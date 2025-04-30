package com.maykon.hubstop.integration.service.interfaces;

public interface AuthService {

    /**
     * Generates the authorization URL for the OAuth2 flow.
     *
     * @return The authorization URL as a String.
     */
    String getAuthorizationUrl();

    /**
     * Exchanges the authorization code for an access token.
     *
     * @param code The authorization code received from the OAuth2 provider.
     */
    void exchangeCodeForToken(String code);
}
