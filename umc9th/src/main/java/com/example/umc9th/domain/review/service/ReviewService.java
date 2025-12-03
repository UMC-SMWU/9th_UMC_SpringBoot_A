package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
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
                .orElseThrow(() -> new RuntimeException("STORE_NOT_FOUND"));

        Review review = Review.builder()
                .store(store)
                .rating(dto.rating())
                .content(dto.content())
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);

        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(review.getId())
                .storeId(store.getId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}