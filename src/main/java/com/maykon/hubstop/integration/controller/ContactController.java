package com.maykon.hubstop.integration.controller;

import com.maykon.hubstop.integration.model.dto.ContactRequest;
import com.maykon.hubstop.integration.model.dto.WebhookEventDTO;
import com.maykon.hubstop.integration.service.ContactService;
import com.maykon.hubstop.integration.service.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;
    private final WebhookService webhookService;

    @PostMapping
    public ResponseEntity<Void> createContact(@RequestBody ContactRequest request) {
        contactService.createContact(request);
        return ResponseEntity.created(URI.create("/contacts")).build();
    }

    @PostMapping("/webhook")
    @ResponseStatus(HttpStatus.NO_CONTENT) // HubSpot espera 2xx
    public void receiveWebhook(@RequestBody List<WebhookEventDTO> events) {
        webhookService.handleWebhookEvents(events);
    }
}
