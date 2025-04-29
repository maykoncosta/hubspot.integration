package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.model.dto.TokenResponse;
import com.maykon.hubstop.integration.service.interfaces.OAuthTokenExchanger;
import com.maykon.hubstop.integration.service.interfaces.OAuthUrlBuilder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final OAuthUrlBuilder builder;
    private final OAuthTokenExchanger exchanger;
    private final TokenStorageService tokenStorage;

    public AuthService(HubSpotOAuthUrlBuilder builder, OAuthTokenExchanger exchanger, TokenStorageService tokenStorage) {
        this.builder = builder;
        this.exchanger = exchanger;
        this.tokenStorage = tokenStorage;
    }

    public String getAuthorizationUrl() {
        return builder.buildAuthorizationUrl();
    }

    public void exchangeCodeForToken(String code) {
        TokenResponse tokenResponse = exchanger.exchange(code);
        tokenStorage.save(tokenResponse);
    }

    public String getTokenFromRedis() {
        return tokenStorage.getLatestAccessToken();
    }
}
