package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            Long storeId,
            BigDecimal rating,
            String content,
            LocalDateTime createdAt
    ) {}
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Integer score,
            String body,
            LocalDate createdAt
    ){}
}