CREATE TABLE beroep
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    persoon_id BIGINT                NULL,
    beroep     VARCHAR(255)          NULL,
    CONSTRAINT pk_beroep PRIMARY KEY (id)
);

CREATE TABLE document
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    document_type_id BIGINT                NULL,
    `path`           VARCHAR(255)          NULL,
    transcript       VARCHAR(255)          NULL,
    persoon_id       BIGINT                NULL,
    relatie_id       BIGINT                NULL,
    CONSTRAINT pk_document PRIMARY KEY (id)
);

CREATE TABLE document_type
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    omschrijving VARCHAR(255)          NULL,
    code         VARCHAR(255)          NULL,
    CONSTRAINT pk_document_type PRIMARY KEY (id)
);

CREATE TABLE gebruiker
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    voornaam      VARCHAR(255)          NOT NULL,
    naam          VARCHAR(255)          NOT NULL,
    email         VARCHAR(100)          NOT NULL,
    wachtwoord    VARCHAR(255)          NOT NULL,
    aangemaakt_op date                  NULL,
    gewijzigd_op  date                  NULL,
    CONSTRAINT pk_gebruiker PRIMARY KEY (id)
);

CREATE TABLE gebruiker_machtiging
(
    gebruiker_id  BIGINT NOT NULL,
    machtiging_id BIGINT NOT NULL
);

CREATE TABLE genealogisch_driekhoekje
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    kind_id    BIGINT                NULL,
    ouder_1_id BIGINT                NULL,
    ouder_2_id BIGINT                NULL,
    CONSTRAINT pk_genealogisch_driekhoekje PRIMARY KEY (id)
);

CREATE TABLE info_type
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    omschrijving VARCHAR(255)          NULL,
    CONSTRAINT pk_info_type PRIMARY KEY (id)
);

CREATE TABLE machtigingen
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    machtiging VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_machtigingen PRIMARY KEY (id)
);

CREATE TABLE natuurlijk_persoon
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    naam         VARCHAR(255)          NULL,
    voornaam     VARCHAR(255)          NULL,
    geboren_op   date                  NULL,
    geboren_te   VARCHAR(255)          NULL,
    overleden_op date                  NULL,
    overleden_te VARCHAR(255)          NULL,
    geslacht     VARCHAR(255)          NULL,
    CONSTRAINT pk_natuurlijk_persoon PRIMARY KEY (id)
);

CREATE TABLE natuurlijk_persoon_info
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    persoon_id    BIGINT                NULL,
    kan_lezen     BIT(1)                NULL,
    kan_schrijven BIT(1)                NULL,
    kan_rekenen   BIT(1)                NULL,
    CONSTRAINT pk_natuurlijk_persoon_info PRIMARY KEY (id)
);

CREATE TABLE onderzoeksinfo
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    persoon_id         BIGINT                NULL,
    info_type_id       BIGINT                NULL,
    link_naar_document VARCHAR(255)          NULL,
    link_naar_bron     VARCHAR(255)          NULL,
    extra_info         VARCHAR(255)          NULL,
    CONSTRAINT pk_onderzoeksinfo PRIMARY KEY (id)
);

CREATE TABLE relatie
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    persoon_1_id  BIGINT                NULL,
    persoon_2_id  BIGINT                NULL,
    gehuwd_op     date                  NULL,
    gescheiden_op date                  NULL,
    gehuwd_te     VARCHAR(255)          NULL,
    uit_elkaar    BIT(1)                NULL,
    CONSTRAINT pk_relatie PRIMARY KEY (id)
);

ALTER TABLE gebruiker
    ADD CONSTRAINT uc_gebruiker_email UNIQUE (email);

ALTER TABLE natuurlijk_persoon_info
    ADD CONSTRAINT uc_natuurlijk_persoon_info_persoon UNIQUE (persoon_id);

ALTER TABLE beroep
    ADD CONSTRAINT FK_BEROEP_ON_PERSOON FOREIGN KEY (persoon_id) REFERENCES natuurlijk_persoon (id);

ALTER TABLE document
    ADD CONSTRAINT FK_DOCUMENT_ON_DOCUMENT_TYPE FOREIGN KEY (document_type_id) REFERENCES document_type (id);

ALTER TABLE document
    ADD CONSTRAINT FK_DOCUMENT_ON_PERSOON FOREIGN KEY (persoon_id) REFERENCES natuurlijk_persoon (id);

ALTER TABLE document
    ADD CONSTRAINT FK_DOCUMENT_ON_RELATIE FOREIGN KEY (relatie_id) REFERENCES relatie (id);

ALTER TABLE genealogisch_driekhoekje
    ADD CONSTRAINT FK_GENEALOGISCH_DRIEKHOEKJE_ON_KIND FOREIGN KEY (kind_id) REFERENCES natuurlijk_persoon (id);

ALTER TABLE genealogisch_driekhoekje
    ADD CONSTRAINT FK_GENEALOGISCH_DRIEKHOEKJE_ON_OUDER_1 FOREIGN KEY (ouder_1_id) REFERENCES natuurlijk_persoon (id);

ALTER TABLE genealogisch_driekhoekje
    ADD CONSTRAINT FK_GENEALOGISCH_DRIEKHOEKJE_ON_OUDER_2 FOREIGN KEY (ouder_2_id) REFERENCES natuurlijk_persoon (id);

ALTER TABLE natuurlijk_persoon_info
    ADD CONSTRAINT FK_NATUURLIJK_PERSOON_INFO_ON_PERSOON FOREIGN KEY (persoon_id) REFERENCES natuurlijk_persoon (id);

ALTER TABLE onderzoeksinfo
    ADD CONSTRAINT FK_ONDERZOEKSINFO_ON_INFO_TYPE FOREIGN KEY (info_type_id) REFERENCES info_type (id);

ALTER TABLE onderzoeksinfo
    ADD CONSTRAINT FK_ONDERZOEKSINFO_ON_PERSOON FOREIGN KEY (persoon_id) REFERENCES natuurlijk_persoon (id);

ALTER TABLE relatie
    ADD CONSTRAINT FK_RELATIE_ON_PERSOON_1 FOREIGN KEY (persoon_1_id) REFERENCES natuurlijk_persoon (id);

ALTER TABLE relatie
    ADD CONSTRAINT FK_RELATIE_ON_PERSOON_2 FOREIGN KEY (persoon_2_id) REFERENCES natuurlijk_persoon (id);

ALTER TABLE gebruiker_machtiging
    ADD CONSTRAINT fk_gebmac_on_gebruiker FOREIGN KEY (gebruiker_id) REFERENCES gebruiker (id);

ALTER TABLE gebruiker_machtiging
    ADD CONSTRAINT fk_gebmac_on_machtiging FOREIGN KEY (machtiging_id) REFERENCES machtigingen (id);
