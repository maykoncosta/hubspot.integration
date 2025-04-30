package com.maykon.hubstop.integration.exception;

public class RateLimitedException extends RuntimeException {
    public RateLimitedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RateLimitedException(String message) {
        super(message);
    }
}
