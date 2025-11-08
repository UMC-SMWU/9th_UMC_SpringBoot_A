package com.umc9th.umc9th.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import com.umc9th.umc9th.domain.review.entity.QReview;
import com.umc9th.umc9th.domain.review.entity.Review;
import com.umc9th.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    // 쿼리 테스트
    @Override
    public List<Review> searchReview(String query, String type){
        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        if (type.equals("location")){
            // Q클래스와 매칭되게끔 작성
            builder.and(review.store.location.name.contains(query));
        }
        if (type.equals("star")){
            builder.and(review.star.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")){
            
            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(review.store.location.name.contains(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviews = reviewRepository.searchReview(builder);
        return reviews;
    }

}
