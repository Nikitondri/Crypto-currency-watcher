package com.idf.crypto_currency_watcher.service.impl;

import com.idf.crypto_currency_watcher.entity.Crypto;
import com.idf.crypto_currency_watcher.entity.User;
import com.idf.crypto_currency_watcher.entity.UserCrypto;
import com.idf.crypto_currency_watcher.entity.UserCryptoKey;
import com.idf.crypto_currency_watcher.exception.NoSuchRecordException;
import com.idf.crypto_currency_watcher.repository.CryptoRepository;
import com.idf.crypto_currency_watcher.repository.UserCryptoRepository;
import com.idf.crypto_currency_watcher.repository.UserRepository;
import com.idf.crypto_currency_watcher.service.UserCryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCryptoServiceImpl implements UserCryptoService {

    private final UserCryptoRepository userCryptoRepository;
    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;

    @Autowired
    public UserCryptoServiceImpl(UserCryptoRepository userCryptoRepository, UserRepository userRepository, CryptoRepository cryptoRepository) {
        this.userCryptoRepository = userCryptoRepository;
        this.userRepository = userRepository;
        this.cryptoRepository = cryptoRepository;
    }

    @Override
    public void notifyUser(String name, String symbol) {
        Optional<Crypto> cryptoOptional = cryptoRepository.findCryptoBySymbol(symbol);
        if(cryptoOptional.isEmpty()){
            throw new NoSuchRecordException(
                    String.format("Crypto with id=%s not found for notify", symbol)
            );
        }
        Crypto crypto = cryptoOptional.get();
        User user = userRepository.save(new User(name));
        UserCrypto userCrypto = new UserCrypto(
                new UserCryptoKey(crypto.getId(), user.getId()),
                user,
                crypto,
                crypto.getCost()
        );
        userCryptoRepository.save(userCrypto);
    }
}
