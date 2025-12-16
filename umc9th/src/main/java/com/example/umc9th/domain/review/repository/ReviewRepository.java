package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 가게의 리뷰를 페이징 조회
    Page<Review> findAllByStore(Store store, Pageable pageable);
    // member 기준으로 내가 쓴 리뷰 페이징 조회
    Page<Review> findAllByMember(Member member, Pageable pageable);
}