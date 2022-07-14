package com.idf.crypto_currency_watcher.repository;

import com.idf.crypto_currency_watcher.entity.UserCrypto;
import com.idf.crypto_currency_watcher.entity.UserCryptoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCryptoRepository extends JpaRepository<UserCrypto, UserCryptoKey> {

}
