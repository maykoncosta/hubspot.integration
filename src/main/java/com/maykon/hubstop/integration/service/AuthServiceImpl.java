package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.model.dto.TokenResponse;
import com.maykon.hubstop.integration.service.interfaces.AuthService;
import com.maykon.hubstop.integration.service.interfaces.OAuthTokenExchanger;
import com.maykon.hubstop.integration.service.interfaces.OAuthUrlBuilder;
import com.maykon.hubstop.integration.service.interfaces.TokenStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final OAuthUrlBuilder builder;
    private final OAuthTokenExchanger exchanger;
    private final TokenStorageService tokenStorage;

    @Override
    public String getAuthorizationUrl() {
        return builder.buildAuthorizationUrl();
    }

    @Override
    public void exchangeCodeForToken(String code) {
        TokenResponse tokenResponse = exchanger.exchange(code);
        tokenStorage.save(tokenResponse);
    }

}
