ALTER TABLE partnerDB
    RENAME COLUMN id_account TO id_partner;

ALTER TABLE partnerDB
    DROP CONSTRAINT account_pkey;

ALTER TABLE partnerDB
    ADD CONSTRAINT partner_pkey PRIMARY KEY (id_partner);