package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.constant.UserMissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class UserMissionItemView {
	private Long userMissionId;
	private UserMissionStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime completedAt;

	private Long missionId;
	private String title;
	private LocalDate deadline;

	private Long storeId;
	private String storeName;
}
