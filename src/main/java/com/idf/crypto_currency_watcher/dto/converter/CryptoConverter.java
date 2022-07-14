package com.idf.crypto_currency_watcher.dto.converter;

import com.idf.crypto_currency_watcher.dto.CryptoDTO;
import com.idf.crypto_currency_watcher.entity.Crypto;
import org.springframework.stereotype.Component;

@Component
public class CryptoConverter {
    public CryptoDTO toDTO(Crypto obj) {
        return CryptoDTO.builder()
                .id(obj.getId())
                .symbol(obj.getSymbol())
                .cost(obj.getCost())
                .build();
    }
}
