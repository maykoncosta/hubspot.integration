package com.maykon.hubstop.integration.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String encryptedAccessToken;

    @Column
    private String encryptedRefreshToken;

    @Column
    private Long expiresIn;

    @Column
    private Instant createdAt;

    @Column
    private boolean expired;

}
