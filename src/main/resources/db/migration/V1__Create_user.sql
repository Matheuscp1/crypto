CREATE TABLE users
(
    id    VARCHAR(36) NOT NULL,
    email VARCHAR(80) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);