package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.exception.WebhookProcessingException;
import com.maykon.hubstop.integration.model.EventEntity;
import com.maykon.hubstop.integration.model.dto.WebhookEventDTO;
import com.maykon.hubstop.integration.repository.EventRepository;
import com.maykon.hubstop.integration.service.interfaces.WebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class WebhookServiceImpl implements WebhookService {

    private final EventRepository eventRepository;

    @Override
    public void handleWebhookEvents(List<WebhookEventDTO> events) {
        try {
            processEvents(events);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new WebhookProcessingException(e.getMessage());
        }
    }

    private void processEvents(List<WebhookEventDTO> events) {
        for (WebhookEventDTO event : events) {
            if ("contact.creation".equalsIgnoreCase(event.subscriptionType())) {
                log.info("New contact created! ID: {}, Portal: {}, Subscription: {}", event.objectId(), event.portalId(), event.subscriptionType());
                eventRepository.save(getEventEntity(event));
            } else {
                log.warn("Ignored event. Unsupported type: {}", event.subscriptionType());
            }
        }
    }

    private EventEntity getEventEntity(WebhookEventDTO event) {
        return EventEntity.builder()
                .subscriptionType(event.subscriptionType())
                .objectId(event.objectId())
                .eventId(event.eventId())
                .appId(event.appId())
                .subscriptionId(event.subscriptionId())
                .attemptNumber(event.attemptNumber())
                .changeSource(event.changeSource())
                .changeFlag(event.changeFlag())
                .occurredAt(event.occurredAt())
                .portalId(event.portalId()).build();
    }
}
