package com.maykon.hubstop.integration.service.interfaces;

import com.maykon.hubstop.integration.model.dto.TokenResponse;
import org.springframework.transaction.annotation.Transactional;

public interface TokenStorageService {

    /**
     * Saves the token response to the database.
     *
     * @param response the token response to save
     */
    @Transactional
    void save(TokenResponse response);

    /**
     * Retrieves the latest access token from the database.
     *
     * @return the latest access token
     */
    @Transactional
    String getLatestAccessToken();
}
