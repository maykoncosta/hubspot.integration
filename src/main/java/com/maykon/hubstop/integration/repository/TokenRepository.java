package com.maykon.hubstop.integration.repository;

import com.maykon.hubstop.integration.model.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findTopByOrderByIdDesc();
}
