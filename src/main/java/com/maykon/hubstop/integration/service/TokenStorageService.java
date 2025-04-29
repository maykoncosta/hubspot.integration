package com.maykon.hubstop.integration.service;

import com.maykon.hubstop.integration.config.security.CryptoService;
import com.maykon.hubstop.integration.model.TokenEntity;
import com.maykon.hubstop.integration.model.dto.TokenResponse;
import com.maykon.hubstop.integration.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenStorageService {

    private final TokenRepository repository;
    private final CryptoService cryptoService;
    private final HubSpotTokenExchanger hubSpotTokenExchanger;

    public void save(TokenResponse response) {
        log.info("Saving token");
        var entity = TokenEntity.builder()
                .encryptedAccessToken(cryptoService.encrypt(response.accessToken()))
                .encryptedRefreshToken(cryptoService.encrypt(response.refreshToken()))
                .expiresIn(response.expiresAt())
                .createdAt(Instant.now())
                .expired(false)
                .build();
        repository.save(entity);
        log.info("Token saved");
    }

    public String getLatestAccessToken() {
        log.info("Getting latest access token");
        TokenEntity token = repository.findTopByOrderByIdDesc();
        if(token == null) {
            log.info("Token not found");
            return "Token não encontrado";
        }

        Instant now = Instant.now();
        Instant tokenExpiration = token.getCreatedAt().plusSeconds(token.getExpiresIn());

        if (now.isAfter(tokenExpiration)) {
            log.info("Token is expired");
            token.setExpired(true);
            repository.save(token);
            renovarToken(token);
            return "Token expirou, mas renovou com sucesso";
        }

        long remainingSeconds = tokenExpiration.getEpochSecond() - now.getEpochSecond();
        token.setExpiresIn(remainingSeconds);
        repository.save(token);

        log.info("Token is valid");
        return "Token válido";
    }

    private TokenEntity renovarToken(TokenEntity expiredToken) {
        log.info("Renewing token");
        String decryptedRefreshToken = cryptoService.decrypt(expiredToken.getEncryptedRefreshToken());

        TokenResponse response = hubSpotTokenExchanger.refreshAccessToken(decryptedRefreshToken);

        TokenEntity novoToken = TokenEntity.builder()
                .encryptedAccessToken(cryptoService.encrypt(response.accessToken()))
                .encryptedRefreshToken(cryptoService.encrypt(response.refreshToken()))
                .expiresIn(response.expiresAt())
                .createdAt(Instant.now())
                .expired(false)
                .build();

        log.info("Token renewed");
        return repository.save(novoToken);
    }
}

