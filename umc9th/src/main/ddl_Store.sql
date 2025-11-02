CREATE TABLE store
(
    store_id          BIGINT AUTO_INCREMENT NOT NULL,
    name              VARCHAR(100)          NOT NULL,
    address           VARCHAR(255)          NOT NULL,
    verification_code VARCHAR(50)           NOT NULL,
    created_at        datetime              NULL,
    region_id         BIGINT                NOT NULL,
    CONSTRAINT pk_store PRIMARY KEY (store_id)
);

ALTER TABLE store
    ADD CONSTRAINT FK_STORE_ON_REGION FOREIGN KEY (region_id) REFERENCES region (region_id);