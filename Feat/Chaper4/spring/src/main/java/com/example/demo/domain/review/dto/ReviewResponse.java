package com.example.demo.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResponse {

	private Long id;
	private Long storeId;
	private Long userId;
	private Float star;
	private String content;
}
