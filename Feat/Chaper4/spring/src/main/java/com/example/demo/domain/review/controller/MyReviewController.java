package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.converter.MyReviewConverter;
import com.example.demo.domain.review.dto.MyReviewItemView;
import com.example.demo.domain.review.service.ReviewReadService;
import com.example.demo.dto.MyReviewSearchCond;
import com.example.demo.global.api.ApiResponse;
import com.example.demo.global.api.SuccessCode;
import com.example.demo.global.paging.PageQuery;
import com.example.demo.global.paging.PageResponse;
import com.example.demo.global.paging.PageResponseConverter;

import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class MyReviewController {

	private final ReviewReadService reviewReadService;

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<PageResponse<MyReviewItemView>>> myReviews(
		@RequestHeader("X-USER-ID") Long userId,

		@RequestParam(required = false) Long storeId,
		@RequestParam(required = false) String storeName,
		@RequestParam(required = false) Integer ratingBand,

		@PageQuery int page
	) {
		MyReviewSearchCond cond = new MyReviewSearchCond(storeId, storeName, ratingBand);

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
		Page<com.example.demo.dto.MyReviewItemDto> result = reviewReadService.getMyReviews(userId, cond, pageable);

		PageResponse<MyReviewItemView> body =
			PageResponseConverter.from(result, page, MyReviewConverter::toView);

		SuccessCode successCode = SuccessCode.GET_MY_REVIEWS_SUCCESS;
		return ResponseEntity
			.status(successCode.getHttpStatus())
			.body(ApiResponse.success(successCode, body));
	}
}
