package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.region.entity.QRegion;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    public List<Review> searchReview(String query, String type
    ) {
        // Q클래스 정의
        QReview review = QReview.review;
        QRegion region = QRegion.region;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        if ("region".equals(type)) {
            builder.and(region.name.contains(query)); // alias 사용 권장
        } else if ("rating".equals(type)) {
            builder.and(review.rating.goe(new BigDecimal(query)));
        } else if ("both".equals(type)) {
            String[] parts = query.split("&");
            String first = parts.length > 0 ? parts[0] : "";
            String second = parts.length > 1 ? parts[1] : "0";
            builder.and(region.name.contains(first));
            builder.and(review.rating.goe(new BigDecimal(second)));
        }

        return reviewRepository.searchReview(builder);
    }
}
