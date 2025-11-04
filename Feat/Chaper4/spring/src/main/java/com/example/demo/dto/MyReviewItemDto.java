package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public record MyReviewItemDto(
	Long reviewId,
	Long storeId,
	String storeName,
	Float star,
	String content,
	LocalDateTime createdAt,
	List<String> photoUrls
) {}

