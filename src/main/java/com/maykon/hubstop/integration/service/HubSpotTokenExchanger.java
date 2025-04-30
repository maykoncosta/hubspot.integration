package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.exception.TokenExchangeException;
import com.maykon.hubstop.integration.model.dto.TokenResponse;
import com.maykon.hubstop.integration.service.interfaces.OAuthTokenExchanger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HubSpotTokenExchanger implements OAuthTokenExchanger {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${hubspot.oauth.client.id}")
    private String clientId;

    @Value("${hubspot.oauth.client.secret}")
    private String clientSecret;

    @Value("${hubspot.oauth.client.redirect.uri}")
    private String redirectUri;

    @Override
    public TokenResponse exchange(String code) {
        log.info("Exchanging code...");
        TokenResponse tokenResponse = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        String grantType = "authorization_code";
        form.add("grant_type", grantType);
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("redirect_uri", redirectUri);
        form.add("code", code);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(form, headers);

        try {
            ResponseEntity<TokenResponse> response = restTemplate.postForEntity(
                    "https://api.hubapi.com/oauth/v1/token",
                    entity,
                    TokenResponse.class
            );
            tokenResponse = response.getBody();
        } catch (RuntimeException e) {
            log.error("Error exchanging code: {}", e.getMessage());
            throw new TokenExchangeException(e.getMessage());
        }
        log.info("code exchanged successfully");
        return tokenResponse;
    }

    public TokenResponse refreshAccessToken(String refreshToken) {
        log.info("Refreshing access token...");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "refresh_token");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(form, headers);

        try {
            ResponseEntity<TokenResponse> response = restTemplate.postForEntity(
                    "https://api.hubapi.com/oauth/v1/token",
                    entity,
                    TokenResponse.class
            );
            return response.getBody();
        } catch (RuntimeException e) {
            log.error("Error while refreshing token: {}", e.getMessage());
            throw new TokenExchangeException(e.getMessage());
        }
    }

}
