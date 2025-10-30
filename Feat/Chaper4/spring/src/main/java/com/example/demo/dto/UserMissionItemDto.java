package com.example.demo.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.example.demo.domain.mission.constant.UserMissionStatus;

public record UserMissionItemDto(
	Long userMissionId,
	UserMissionStatus status,
	LocalDateTime createdAt,
	LocalDateTime completedAt,
	Long missionId,
	String title,
	LocalDate deadline,
	Long storeId,
	String storeName
) {}
