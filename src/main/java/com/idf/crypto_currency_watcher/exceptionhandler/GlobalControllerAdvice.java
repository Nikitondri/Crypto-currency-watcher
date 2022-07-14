package com.idf.crypto_currency_watcher.exceptionhandler;

import com.idf.crypto_currency_watcher.exception.NoSuchRecordException;
import com.idf.crypto_currency_watcher.exception.ParsingException;
import com.idf.crypto_currency_watcher.exception.ResponseFromApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice("com.idf.crypto_currency_watcher.controller")
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchRecordException.class)
    public String handleNoSuchRecordException(NoSuchRecordException exception){
        log.error(String.format("Handled exception - %s", exception), exception);
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParsingException.class)
    public String handleParseException(ParsingException exception){
        log.error(String.format("Handled exception - %s", exception), exception);
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseFromApiException.class)
    public String handleResponseFromApiException(ResponseFromApiException exception){
        log.error(String.format("Handled exception - %s", exception), exception);
        return exception.getMessage();
    }

}