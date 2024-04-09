ALTER TABLE partnerDB
    ADD COLUMN tx_cnpj VARCHAR(14);

ALTER TABLE partnerDB
    DROP COLUMN tx_hash;
