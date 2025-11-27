package com.example.demo.domain.store.service;


import com.example.demo.domain.mission.dto.UserMissionResponse;
import com.example.demo.domain.review.dto.ReviewCreateRequest;
import com.example.demo.domain.review.dto.ReviewResponse;

public interface StoreService {


	// 가게에 리뷰 추가 (필수)
	ReviewResponse createReview(Long storeId, ReviewCreateRequest request);


	//  미션 도전하기 (필수)
	UserMissionResponse challengeMission(Long missionId);
}
