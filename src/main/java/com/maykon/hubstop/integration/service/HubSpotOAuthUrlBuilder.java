package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.service.interfaces.OAuthUrlBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
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
        log.info("building authorization URL for HubSpot OAuth");
        return UriComponentsBuilder.fromUriString("https://app.hubspot.com/oauth/authorize")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", scope)
                .queryParam("response_type", "code")
                .toUriString();
    }
}
