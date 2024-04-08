CREATE TABLE partnerDB
(
    id_partner character varying(36) NOT NULL,
    tx_name character varying(256) NOT NULL,
    tx_email character varying(256) NOT NULL,
    tx_cnpj VARCHAR(14) NOT NULL,
    CONSTRAINT partner_pkey PRIMARY KEY (id_partner)
);
