package com.example.demo.domain.store.controller;


import com.example.demo.domain.mission.dto.UserMissionResponse;
import com.example.demo.domain.review.dto.ReviewCreateRequest;
import com.example.demo.domain.review.dto.ReviewResponse;

import com.example.demo.domain.store.service.StoreService;
import com.example.demo.global.api.ApiResponse;
import com.example.demo.global.api.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StoreController {

	private final StoreService storeService;



	/**
	 * 2. 가게에 리뷰 추가하기 API
	 *  - 하드코딩 유저 사용 (서비스에서 처리)
	 */
	@PostMapping("/stores/{storeId}/reviews")
	public ResponseEntity<ApiResponse<ReviewResponse>> createReview(
		@PathVariable Long storeId,
		@RequestBody ReviewCreateRequest request
	) {
		ReviewResponse response = storeService.createReview(storeId, request);
		SuccessCode successCode = SuccessCode.CREATE_REVIEW_SUCCESS;

		return ResponseEntity
			.status(successCode.getHttpStatus())
			.body(ApiResponse.success(successCode, response));
	}


	/**
	 * 4. 미션 도전하기 API
	 *
	 */
	@PostMapping("/missions/{missionId}/challenge")
	public ResponseEntity<ApiResponse<UserMissionResponse>> challengeMission(
		@PathVariable Long missionId
	) {
		UserMissionResponse response = storeService.challengeMission(missionId);
		SuccessCode successCode = SuccessCode.CHALLENGE_MISSION_SUCCESS;

		return ResponseEntity
			.status(successCode.getHttpStatus())
			.body(ApiResponse.success(successCode, response));
	}
}
