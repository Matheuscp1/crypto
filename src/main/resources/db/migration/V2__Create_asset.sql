CREATE TABLE asset
(
    id        VARCHAR(36)  NOT NULL,
    wallet_id VARCHAR(36)  NOT NULL,
    symbol    VARCHAR(255) NOT NULL,
    quantity  DECIMAL      NOT NULL,
    price     DECIMAL      NOT NULL,
    value     DECIMAL      NOT NULL,
    CONSTRAINT pk_asset PRIMARY KEY (id)
);

ALTER TABLE asset
    ADD CONSTRAINT FK_ASSET_ON_WALLET FOREIGN KEY (wallet_id) REFERENCES wallets (id) ON DELETE CASCADE;