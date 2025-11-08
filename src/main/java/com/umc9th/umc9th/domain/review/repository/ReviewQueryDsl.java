package com.umc9th.umc9th.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.umc9th.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {
    // 검색 API
    public List<Review> searchReview(
            Predicate predicate
    );
}
