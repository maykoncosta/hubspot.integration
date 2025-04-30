package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.exception.OperationException;
import com.maykon.hubstop.integration.exception.RateLimitedException;
import com.maykon.hubstop.integration.model.dto.ContactRequest;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class HubSpotClient {

    private final TokenStorageServiceImpl tokenStorageService;
    private final RestTemplate restTemplate;

    public void createContact(ContactRequest request) {
        String url = "https://api.hubapi.com/crm/v3/objects/contacts";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenStorageService.getLatestAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> properties = new HashMap<>();
        properties.put("email", request.email());
        properties.put("firstname", request.firstName());
        properties.put("lastname", request.lastName());

        Map<String, Object> body = new HashMap<>();
        body.put("properties", properties);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException.TooManyRequests e) {
            log.error("Rate limit exceeded while creating contact. Message: {}", e.getMessage());
            throw new RateLimitedException("Rate limit exceeded. Please try again later.");
        } catch (HttpClientErrorException e) {
            log.error("Error creating contact: {}", e.getResponseBodyAsString());
            throw new OperationException("Error creating contact: " + e.getMessage());
        }
    }
}
