package com.example.demo.domain.review.service;

import com.example.demo.dto.MyReviewItemDto;
import com.example.demo.dto.MyReviewSearchCond;
import com.example.demo.domain.review.repository.ReviewQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewReadService {
	private final ReviewQueryRepository reviewQueryRepository;

	public Page<MyReviewItemDto> getMyReviews(Long userId, MyReviewSearchCond cond, Pageable pageable){
		return reviewQueryRepository.findMyReviews(userId, cond, pageable);
	}
}

