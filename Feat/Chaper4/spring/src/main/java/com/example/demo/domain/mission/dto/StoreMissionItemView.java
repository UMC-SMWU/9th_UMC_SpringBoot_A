package com.example.demo.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class StoreMissionItemView {
	private Long missionId;
	private String title;
	private String condition;
	private LocalDate deadline;
	private Integer basePoint;

	private Long storeId;
	private String storeName;
}
