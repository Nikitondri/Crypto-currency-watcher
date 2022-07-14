package com.idf.crypto_currency_watcher.controller;

import com.idf.crypto_currency_watcher.service.UserCryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
@Slf4j
public class UserCryptoController {

    private final UserCryptoService userCryptoService;

    @Autowired
    public UserCryptoController(UserCryptoService userCryptoService) {
        this.userCryptoService = userCryptoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void notifyUser(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "symbol") String symbol
    ){
        log.info("executing notifyUser method");
        userCryptoService.notifyUser(name, symbol);
    }
}
