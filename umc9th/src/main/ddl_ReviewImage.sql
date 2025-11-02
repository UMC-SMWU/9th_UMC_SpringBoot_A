CREATE TABLE review_image
(
    review_image_id  BIGINT AUTO_INCREMENT NOT NULL,
    review_id        BIGINT                NOT NULL,
    review_image_url VARCHAR(200)          NOT NULL,
    CONSTRAINT pk_review_image PRIMARY KEY (review_image_id)
);

ALTER TABLE review_image
    ADD CONSTRAINT FK_REVIEW_IMAGE_ON_REVIEW FOREIGN KEY (review_id) REFERENCES reviews (review_id);