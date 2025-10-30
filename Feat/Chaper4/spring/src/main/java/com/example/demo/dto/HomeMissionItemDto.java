package com.example.demo.dto;


import java.time.LocalDate;

public record HomeMissionItemDto(
	Long missionId,
	String title,
	String condition,
	LocalDate deadline,
	Integer basePoint,
	Long storeId,
	String storeName
) {}