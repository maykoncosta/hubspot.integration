package com.maykon.hubstop.integration.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
        @JsonProperty("token_type") String tokenType,
        @JsonProperty("refresh_token") String refreshToken,
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("expires_in") long expiresAt
) {}
