USE crypto_watcher_db;

DROP TABLE IF EXISTS crypto_user;

CREATE TABLE crypto_user(
    crypto_id       INT UNSIGNED        NOT NULL,
    user_id         INT UNSIGNED        NOT NULL,
    current_cost    DOUBLE              NOT NULL,

    PRIMARY KEY (crypto_id, user_id),
    FOREIGN KEY (crypto_id) REFERENCES crypto (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);