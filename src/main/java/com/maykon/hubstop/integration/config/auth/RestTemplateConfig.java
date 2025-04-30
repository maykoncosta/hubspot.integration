package com.maykon.hubstop.integration.config.auth;

import com.maykon.hubstop.integration.service.TokenStorageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final TokenStorageServiceImpl tokenStorage;

    public RestTemplateConfig(TokenStorageServiceImpl tokenStorage) {
        this.tokenStorage = tokenStorage;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new AuthInterceptor(tokenStorage));
        return restTemplate;
    }
}

