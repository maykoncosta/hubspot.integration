package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.service.interfaces.OAuthUrlBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class HubSpotOAuthUrlBuilder implements OAuthUrlBuilder {


    @Value("${hubspot.oauth.client.id}")
    private String clientId;

    @Value("${hubspot.oauth.client.redirect.uri}")
    private String redirectUri;

    @Value("${hubspot.oauth.client.scope}")
    private String scope;

    @Override
    public String buildAuthorizationUrl() {

        return UriComponentsBuilder.fromUriString("https://app.hubspot.com/oauth/authorize")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", URLEncoder.encode(redirectUri, StandardCharsets.UTF_8))
                .queryParam("scope", scope)
                .queryParam("response_type", "code")
                .toUriString();
    }
}
