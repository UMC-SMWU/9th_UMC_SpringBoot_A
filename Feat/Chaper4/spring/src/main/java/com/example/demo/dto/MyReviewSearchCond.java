package com.example.demo.dto;

public record MyReviewSearchCond(
	Long storeId,       // 선택: 특정 가게
	String storeName,   // 선택: 가게명 부분검색
	Integer ratingBand  // 선택: 5,4,3 
) {}

