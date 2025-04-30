package com.maykon.hubstop.integration.service.interfaces;

import com.maykon.hubstop.integration.model.dto.TokenResponse;

public interface OAuthTokenExchanger {
    /**
     * Exchanges the authorization code for an access token.
     *
     * @param code The authorization code received from the OAuth provider.
     * @return The token response containing the access token and other information.
     */
    TokenResponse exchange(String code);
}
