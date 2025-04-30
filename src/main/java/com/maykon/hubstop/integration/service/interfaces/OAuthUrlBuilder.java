package com.maykon.hubstop.integration.service.interfaces;

public interface OAuthUrlBuilder {
    /**
     * Builds the authorization URL for the OAuth2 flow.
     *
     * @return The authorization URL as a String.
     */
    String buildAuthorizationUrl();
}