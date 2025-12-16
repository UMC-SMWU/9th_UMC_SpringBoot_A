package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateReviewDTO createReview(ReviewReqDTO.CreateReviewDTO dto) {

        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // BigDecimal -> Integer 변환 (예: 4.0 -> 4)
        Integer star = dto.rating() == null ? null : dto.rating().intValue();

        Review review = Review.builder()
                .store(store)
                .star(star)
                .content(dto.content())
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);

        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(review.getId())
                .storeId(store.getId())
                .rating(dto.rating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}