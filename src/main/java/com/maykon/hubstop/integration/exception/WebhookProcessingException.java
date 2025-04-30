package com.maykon.hubstop.integration.exception;

public class WebhookProcessingException extends RuntimeException {
    public WebhookProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebhookProcessingException(String message) {
        super(message);
    }
}
