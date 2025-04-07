CREATE TABLE wallets
(
    id      VARCHAR(36) NOT NULL,
    user_id VARCHAR(36),
    CONSTRAINT pk_wallets PRIMARY KEY (id)
);

ALTER TABLE wallets
    ADD CONSTRAINT uc_wallets_user UNIQUE (user_id);

ALTER TABLE wallets
    ADD CONSTRAINT FK_WALLETS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;