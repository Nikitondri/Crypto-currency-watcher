package com.idf.crypto_currency_watcher.exception;

public class ParsingException extends RuntimeException{
    public ParsingException() {
        super();
    }

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingException(Throwable cause) {
        super(cause);
    }

    protected ParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
