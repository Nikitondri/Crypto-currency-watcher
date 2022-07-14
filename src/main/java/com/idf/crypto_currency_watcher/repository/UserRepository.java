package com.idf.crypto_currency_watcher.repository;

import com.idf.crypto_currency_watcher.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
