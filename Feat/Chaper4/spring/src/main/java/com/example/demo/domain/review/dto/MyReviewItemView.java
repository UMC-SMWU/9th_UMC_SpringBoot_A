package com.example.demo.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MyReviewItemView {
	private Long reviewId;
	private Long storeId;
	private String storeName;
	private Float star;
	private String content;
	private LocalDateTime createdAt;
	private List<String> photoUrls;
}
