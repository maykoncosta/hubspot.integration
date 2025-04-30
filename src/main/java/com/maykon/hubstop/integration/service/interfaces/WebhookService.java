package com.maykon.hubstop.integration.service.interfaces;

import com.maykon.hubstop.integration.model.dto.WebhookEventDTO;

import java.util.List;

public interface WebhookService {
    /**
     * Handles incoming webhook events from HubSpot.
     *
     * @param events the list of webhook events to handle
     */
    void handleWebhookEvents(List<WebhookEventDTO> events);
}
