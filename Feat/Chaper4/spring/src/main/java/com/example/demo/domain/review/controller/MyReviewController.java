package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.service.ReviewReadService;
import com.example.demo.dto.MyReviewItemDto;
import com.example.demo.dto.MyReviewSearchCond;
import com.example.demo.global.api.ApiResponse;
import com.example.demo.global.api.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class MyReviewController {

	private final ReviewReadService reviewReadService;

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<Page<MyReviewItemDto>>> myReviews(
		@RequestHeader("X-USER-ID") Long userId, // 임시로 헤더 사용
		@RequestParam(required = false) Long storeId,
		@RequestParam(required = false) String storeName,
		@RequestParam(required = false) Integer ratingBand,
		@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
		Pageable pageable
	) {
		MyReviewSearchCond cond = new MyReviewSearchCond(storeId, storeName, ratingBand);
		Page<MyReviewItemDto> page = reviewReadService.getMyReviews(userId, cond, pageable);

		SuccessCode successCode = SuccessCode.GET_MY_REVIEWS_SUCCESS;

		return ResponseEntity
			.status(successCode.getHttpStatus())
			.body(ApiResponse.success(successCode, page));
	}
}
