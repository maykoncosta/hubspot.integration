package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.model.dto.ContactRequest;
import com.maykon.hubstop.integration.service.interfaces.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final HubSpotClient hubSpotClient;

    @Override
    public void createContact(ContactRequest request) {
        hubSpotClient.createContact(request);
    }
}

