package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.UserMissionItemView;
import com.example.demo.domain.mission.service.UserMissionQueryService;
import com.example.demo.global.api.ApiResponse;
import com.example.demo.global.api.SuccessCode;
import com.example.demo.global.paging.PageQuery;
import com.example.demo.global.paging.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserMissionQueryController {

	private final UserMissionQueryService userMissionQueryService;


	@GetMapping("/missions/me/in-progress")
	public ResponseEntity<ApiResponse<PageResponse<UserMissionItemView>>> myInProgress(
		@RequestHeader("X-USER-ID") Long userId,
		@PageQuery int page
	) {
		PageResponse<UserMissionItemView> data = userMissionQueryService.getMyInProgress(userId, page);
		SuccessCode successCode = SuccessCode.READ_SUCCESS;
		return ResponseEntity
			.status(successCode.getHttpStatus())
			.body(ApiResponse.success(successCode, data));
	}
}
