CREATE TABLE gebruiker
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    voornaam      VARCHAR(255)                            NOT NULL,
    naam          VARCHAR(255)                            NOT NULL,
    email         VARCHAR(100)                            NOT NULL,
    wachtwoord    VARCHAR(255)                            NOT NULL,
    aangemaakt_op date,
    gewijzigd_op  date,
    CONSTRAINT pk_gebruiker PRIMARY KEY (id)
);

ALTER TABLE gebruiker
    ADD CONSTRAINT uc_gebruiker_email UNIQUE (email);