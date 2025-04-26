package com.maykon.hubstop.integration.service.interfaces;

import com.maykon.hubstop.integration.model.dto.TokenResponse;

public interface OAuthTokenExchanger {
    TokenResponse exchange(String code);
}
