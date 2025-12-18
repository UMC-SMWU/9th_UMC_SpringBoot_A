package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.UserMissionItemView;
import com.example.demo.dto.UserMissionItemDto;

public class UserMissionConverter {

	public static UserMissionItemView toView(UserMissionItemDto dto) {
		return UserMissionItemView.builder()
			.userMissionId(dto.userMissionId())
			.status(dto.status())
			.createdAt(dto.createdAt())
			.completedAt(dto.completedAt())
			.missionId(dto.missionId())
			.title(dto.title())
			.deadline(dto.deadline())
			.storeId(dto.storeId())
			.storeName(dto.storeName())
			.build();
	}
}
