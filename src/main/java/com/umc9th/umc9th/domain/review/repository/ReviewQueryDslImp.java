package com.umc9th.umc9th.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc9th.umc9th.domain.review.entity.QReview;
import com.umc9th.umc9th.domain.review.entity.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImp implements ReviewQueryDsl{
    private final ReviewRepository reviewRepository;
    private final EntityManager em;

    // 검색 API
    @Override
    public List<Review> searchReview(
        Predicate predicate
    ){
        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q클래스 선언
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();


    }
}
