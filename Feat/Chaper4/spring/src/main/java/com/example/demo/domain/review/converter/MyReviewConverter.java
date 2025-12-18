package com.example.demo.domain.review.converter;

import com.example.demo.domain.review.dto.MyReviewItemView;
import com.example.demo.dto.MyReviewItemDto;

public class MyReviewConverter {

	public static MyReviewItemView toView(MyReviewItemDto dto) {
		return MyReviewItemView.builder()
			.reviewId(dto.reviewId())
			.storeId(dto.storeId())
			.storeName(dto.storeName())
			.star(dto.star())
			.content(dto.content())
			.createdAt(dto.createdAt())
			.photoUrls(dto.photoUrls())
			.build();
	}
}
