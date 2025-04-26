package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.model.dto.TokenResponse;
import com.maykon.hubstop.integration.service.interfaces.OAuthTokenExchanger;
import com.maykon.hubstop.integration.service.interfaces.OAuthUrlBuilder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final OAuthUrlBuilder builder;
    private final OAuthTokenExchanger exchanger;

    public AuthService(HubSpotOAuthUrlBuilder builder, OAuthTokenExchanger exchanger) {
        this.builder = builder;
        this.exchanger = exchanger;
    }

    public String getAuthorizationUrl() {
        return builder.buildAuthorizationUrl();
    }

    public void exchangeCodeForToken(String code) {
        TokenResponse response = exchanger.exchange(code);

        System.out.println("Access token: " + response);
    }
}
