USE crypto_watcher_db;

DROP TABLE IF EXISTS user;

CREATE TABLE user(
    id      INT UNSIGNED    NOT NULL    AUTO_INCREMENT,
    name    VARCHAR(255)    NOT NULL,

    PRIMARY KEY (id)
);