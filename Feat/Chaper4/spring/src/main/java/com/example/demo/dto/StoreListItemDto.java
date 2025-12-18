package com.example.demo.dto;

import java.time.LocalDateTime;

public record StoreListItemDto(
	Long id,
	String name,
	String region,
	LocalDateTime createdAt
) {}