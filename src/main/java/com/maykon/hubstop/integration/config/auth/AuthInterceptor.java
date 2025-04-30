package com.maykon.hubstop.integration.config.auth;

import com.maykon.hubstop.integration.service.TokenStorageServiceImpl;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.ClientHttpRequestExecution;

import java.io.IOException;

public class AuthInterceptor implements ClientHttpRequestInterceptor {

    private final TokenStorageServiceImpl tokenStorage;

    public AuthInterceptor(TokenStorageServiceImpl tokenStorage) {
        this.tokenStorage = tokenStorage;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        if (shouldSkipToken(request)) {
            return execution.execute(request, body);
        }

        String accessToken = tokenStorage.getLatestAccessToken();
        request.getHeaders().setBearerAuth(accessToken);
        return execution.execute(request, body);
    }

    private boolean shouldSkipToken(HttpRequest request) {
        return request.getURI().toString().contains("/token");
    }
}

