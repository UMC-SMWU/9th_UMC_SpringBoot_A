package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.StoreMissionItemView;
import com.example.demo.domain.mission.entity.Mission;

public class MissionConverter {

	public static StoreMissionItemView toStoreMissionItem(Mission m) {
		return StoreMissionItemView.builder()
			.missionId(m.getId())
			.title(m.getTitle())
			.condition(m.getCondition())
			.deadline(m.getDeadline())
			.basePoint(m.getBasePoint())
			.storeId(m.getStore().getId())
			.storeName(m.getStore().getName())
			.build();
	}
}
