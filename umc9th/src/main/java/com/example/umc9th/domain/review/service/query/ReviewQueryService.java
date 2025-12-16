package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {
    // 검색 API
    List<Review> searchReview (
            String filter,
            String type
    ) throws Exception;

    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);
    ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long memberId, Integer page);
}
