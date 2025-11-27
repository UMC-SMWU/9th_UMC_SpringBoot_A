package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.constant.UserMissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserMissionResponse {

	private Long id;
	private Long userId;
	private Long missionId;
	private UserMissionStatus status;
	private Integer earnedPoint;
	private LocalDateTime completedAt;
}
