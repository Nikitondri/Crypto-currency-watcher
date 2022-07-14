package com.idf.crypto_currency_watcher.controller;

import com.idf.crypto_currency_watcher.dto.CryptoDTO;
import com.idf.crypto_currency_watcher.entity.Crypto;
import com.idf.crypto_currency_watcher.service.CryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cryptos")
@Slf4j
public class CryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CryptoDTO> getAllCrypto(){
        log.info("executing getAllCrypto method");
        return cryptoService.findAll();
    }

    @GetMapping("/cost/{symbol}")
    public Double getBySymbol(@PathVariable String symbol){
        log.info("executing getBySymbol method");
        return cryptoService.getBySymbol(symbol).getCost();
    }

}
