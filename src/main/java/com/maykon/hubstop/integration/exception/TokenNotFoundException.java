package com.maykon.hubstop.integration.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundException(String message) {
        super(message);
    }
}
