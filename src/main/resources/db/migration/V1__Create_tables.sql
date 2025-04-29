CREATE TABLE tokens (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        encrypted_access_token VARCHAR(2048) NOT NULL,
                        encrypted_refresh_token VARCHAR(2048),
                        expires_in BIGINT,
                        created_at TIMESTAMP,
                        expired BOOLEAN
);
