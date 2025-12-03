package com.example.umc9th.domain.review.dto.req;

import lombok.Getter;
import java.math.BigDecimal;

public class ReviewReqDTO {

    public record CreateReviewDTO(
            Long storeId,
            BigDecimal rating,
            String content
    ) {}
}