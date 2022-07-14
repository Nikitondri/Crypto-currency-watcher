USE crypto_watcher_db;

DROP TABLE IF EXISTS crypto;

CREATE TABLE crypto(
    id      INT UNSIGNED        NOT NULL,
    symbol  CHAR(3)             NOT NULL,
    cost    DOUBLE              NOT NULL,

    PRIMARY KEY (id)
);