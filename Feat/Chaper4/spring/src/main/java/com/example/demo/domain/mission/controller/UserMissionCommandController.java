package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.UserMissionItemView;
import com.example.demo.domain.mission.service.UserMissionCommandService;
import com.example.demo.global.api.ApiResponse;
import com.example.demo.global.api.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserMissionCommandController {

	private final UserMissionCommandService userMissionCommandService;


	@PatchMapping("/user-missions/{userMissionId}/complete")
	public ResponseEntity<ApiResponse<UserMissionItemView>> complete(
		@RequestHeader("X-USER-ID") Long userId,
		@PathVariable Long userMissionId
	) {
		UserMissionItemView data = userMissionCommandService.complete(userId, userMissionId);
		SuccessCode successCode = SuccessCode.UPDATE_SUCCESS;
		return ResponseEntity
			.status(successCode.getHttpStatus())
			.body(ApiResponse.success(successCode, data));
	}
}
