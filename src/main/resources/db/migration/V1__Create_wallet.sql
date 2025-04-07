CREATE TABLE wallets
(
    id      VARCHAR(36) NOT NULL,
    user_id VARCHAR(36),
    email VARCHAR(100) NOT NULL,
    CONSTRAINT pk_wallets PRIMARY KEY (id)
);

ALTER TABLE wallets
    ADD CONSTRAINT uc_wallets_email UNIQUE (email);
