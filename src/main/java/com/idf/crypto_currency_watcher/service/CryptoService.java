package com.idf.crypto_currency_watcher.service;

import com.idf.crypto_currency_watcher.dto.CryptoDTO;
import com.idf.crypto_currency_watcher.entity.Crypto;
import com.idf.crypto_currency_watcher.exception.ResponseFromApiException;

import java.util.List;

public interface CryptoService {
    List<CryptoDTO> findAll();

    Crypto getBySymbol(String symbol);

    void refreshCost() throws ResponseFromApiException;



}
