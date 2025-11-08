package com.umc9th.umc9th.domain.review.service;

import com.umc9th.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {
    public List<Review> searchReview(String query, String type);
}
