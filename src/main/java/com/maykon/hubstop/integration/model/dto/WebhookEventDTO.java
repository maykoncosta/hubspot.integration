package com.maykon.hubstop.integration.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WebhookEventDTO(
        @JsonProperty("objectId") Long objectId,
        @JsonProperty("subscriptionType") String subscriptionType,
        @JsonProperty("portalId") Long portalId,
        @JsonProperty("appId") Long appId,
        @JsonProperty("occurredAt") Long occurredAt,
        @JsonProperty("changeSource") String changeSource,
        @JsonProperty("changeFlag") String changeFlag,
        @JsonProperty("attemptNumber") Long attemptNumber,
        @JsonProperty("subscriptionId") Long subscriptionId,
        @JsonProperty("eventId") Long eventId
) {
}
