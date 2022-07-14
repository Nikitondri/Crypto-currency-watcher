package com.idf.crypto_currency_watcher.exception;

public class ResponseFromApiException extends Exception{
    public ResponseFromApiException() {
        super();
    }

    public ResponseFromApiException(String message) {
        super(message);
    }

    public ResponseFromApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseFromApiException(Throwable cause) {
        super(cause);
    }

    protected ResponseFromApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
