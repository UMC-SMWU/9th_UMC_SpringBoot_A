package com.example.demo.domain.review.controller;


import com.example.demo.dto.MyReviewItemDto;
import com.example.demo.dto.MyReviewSearchCond;
import com.example.demo.domain.review.service.ReviewReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class MyReviewController {

	private final ReviewReadService reviewReadService;

	@GetMapping("/me")
	public Page<MyReviewItemDto> myReviews(
		@RequestHeader("X-USER-ID") Long userId, // 임시로 헤더 사용
		@RequestParam(required = false) Long storeId,
		@RequestParam(required = false) String storeName,
		@RequestParam(required = false) Integer ratingBand, // 5|4|3|2|1
		@PageableDefault(size = 10, sort = "createdAt", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable
	){
		var cond = new MyReviewSearchCond(storeId, storeName, ratingBand);
		return reviewReadService.getMyReviews(userId, cond, pageable);
	}
}
