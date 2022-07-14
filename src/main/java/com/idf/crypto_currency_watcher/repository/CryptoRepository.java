package com.idf.crypto_currency_watcher.repository;

import com.idf.crypto_currency_watcher.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CryptoRepository extends JpaRepository<Crypto, Integer> {
    Optional<Crypto> findCryptoBySymbol(String symbol);

}
