CREATE TABLE tokens (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        encrypted_access_token VARCHAR(2048) NOT NULL,
                        encrypted_refresh_token VARCHAR(2048),
                        expires_in BIGINT,
                        created_at TIMESTAMP,
                        expired BOOLEAN
);
CREATE TABLE events (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          app_id NUMERIC NOT NULL,
                          event_id NUMERIC NOT NULL,
                          subscription_id NUMERIC NOT NULL,
                          portal_id NUMERIC NOT NULL,
                          occurred_at BIGINT NOT NULL,
                          subscription_type VARCHAR(255) NOT NULL,
                          attempt_number NUMERIC NOT NULL,
                          object_id BIGINT NOT NULL,
                          change_source VARCHAR(255) NOT NULL,
                          change_flag VARCHAR(255) NOT NULL
);
