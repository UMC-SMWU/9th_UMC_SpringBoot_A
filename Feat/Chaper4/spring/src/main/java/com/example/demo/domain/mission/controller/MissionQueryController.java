package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.StoreMissionItemView;
import com.example.demo.domain.mission.service.MissionQueryService;
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
public class MissionQueryController {

	private final MissionQueryService missionQueryService;


	@GetMapping("/stores/{storeId}/missions")
	public ResponseEntity<ApiResponse<PageResponse<StoreMissionItemView>>> storeMissions(
		@PathVariable Long storeId,
		@PageQuery int page
	) {
		PageResponse<StoreMissionItemView> data = missionQueryService.getStoreMissions(storeId, page);
		SuccessCode successCode = SuccessCode.READ_SUCCESS;
		return ResponseEntity
			.status(successCode.getHttpStatus())
			.body(ApiResponse.success(successCode, data));
	}
}
