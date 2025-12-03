package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            Long storeId,
            BigDecimal rating,
            String content,
            LocalDateTime createdAt
    ) {}
}