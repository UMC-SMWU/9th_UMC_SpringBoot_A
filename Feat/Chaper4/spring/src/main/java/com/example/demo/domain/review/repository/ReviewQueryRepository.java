package com.example.demo.domain.review.repository;


import com.example.demo.dto.MyReviewItemDto;
import com.example.demo.dto.MyReviewSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
	Page<MyReviewItemDto> findMyReviews(Long userId, MyReviewSearchCond cond, Pageable pageable);
}
