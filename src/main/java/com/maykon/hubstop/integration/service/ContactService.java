package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.model.dto.ContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final HubSpotClient hubSpotClient;

    public void createContact(ContactRequest request) {
        hubSpotClient.createContact(request);
    }
}

