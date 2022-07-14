package com.idf.crypto_currency_watcher.service.impl;

import com.idf.crypto_currency_watcher.dto.CryptoDTO;
import com.idf.crypto_currency_watcher.dto.converter.CryptoConverter;
import com.idf.crypto_currency_watcher.entity.Crypto;
import com.idf.crypto_currency_watcher.entity.UserCrypto;
import com.idf.crypto_currency_watcher.exception.NoSuchRecordException;
import com.idf.crypto_currency_watcher.exception.ResponseFromApiException;
import com.idf.crypto_currency_watcher.repository.CryptoRepository;
import com.idf.crypto_currency_watcher.repository.UserCryptoRepository;
import com.idf.crypto_currency_watcher.service.CryptoService;
import com.idf.crypto_currency_watcher.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CryptoServiceImpl implements CryptoService {

    private final CryptoRepository cryptoRepository;
    private final UserCryptoRepository userCryptoRepository;
    private final CryptoConverter converter;

    @Autowired
    public CryptoServiceImpl(CryptoRepository cryptoRepository, UserCryptoRepository userCryptoRepository, CryptoConverter converter) {
        this.cryptoRepository = cryptoRepository;
        this.userCryptoRepository = userCryptoRepository;
        this.converter = converter;
    }

    @Override
    @Transactional
    public List<CryptoDTO> findAll() {
        return cryptoRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Crypto getBySymbol(String symbol) {
        Optional<Crypto> cryptoOptional = cryptoRepository.findCryptoBySymbol(symbol);
        if(cryptoOptional.isEmpty()){
            throw new NoSuchRecordException(
                    String.format("Crypto with id=%s not found for getting", symbol)
            );
        }
        return cryptoOptional.get();
    }

    @Override
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void refreshCost() throws ResponseFromApiException {
        List<Crypto> cryptoList = cryptoRepository.findAll();
        Map<String, Double> mapCryptosCost = getCryptosCost();
        for(Crypto crypto : cryptoList){
            crypto.setCost(mapCryptosCost.get(crypto.getSymbol()));
        }
        log.debug("Refresh finished");
        checkChangeCost(mapCryptosCost);
    }

    private Map<String, Double> getCryptosCost() throws ResponseFromApiException {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://api.coinlore.net/api/ticker/?id=90,80,48543";
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        if(response.getStatusCode() != HttpStatus.OK){
            throw new ResponseFromApiException("Server response error");
        }
        return JsonUtil.jsonToMapCryptoCost(response.getBody());
    }

    private void checkChangeCost(Map<String, Double> mapCryptosCost){
        List<UserCrypto> userCryptoList = userCryptoRepository.findAll();
        Double newCost;
        Double oldCost;
        double percent;
        for(UserCrypto userCrypto : userCryptoList){
            oldCost = userCrypto.getCurrentCost();
            newCost = mapCryptosCost.get(userCrypto.getCrypto().getSymbol());
            percent = ((oldCost - newCost) / oldCost) * 100;
            if (Math.abs(percent) > 1) {
                log.warn(String.format(
                        "%s; %s; %f",
                        userCrypto.getCrypto().getSymbol(),
                        userCrypto.getUser().getName(),
                        percent)
                );
            }
        }
    }
}
