CREATE TABLE IF NOT EXISTS accounts
(
    id       VARCHAR(255) PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    currency VARCHAR(50)  NOT NULL,
    balance  DOUBLE       NOT NULL,
    reverver DOUBLE       NOT NULL,
    version  BIGINT       NOT NULL
);