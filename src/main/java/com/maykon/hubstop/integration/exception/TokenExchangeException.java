package com.maykon.hubstop.integration.exception;

public class TokenExchangeException extends RuntimeException {
    public TokenExchangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExchangeException(String message) {
        super(message);
    }
}
