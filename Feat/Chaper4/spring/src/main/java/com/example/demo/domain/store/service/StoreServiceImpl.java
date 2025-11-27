package com.example.demo.domain.store.service;


import com.example.demo.domain.mission.constant.UserMissionStatus;

import com.example.demo.domain.mission.dto.UserMissionResponse;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.UserMission;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.mission.repository.UserMissionRepository;
import com.example.demo.domain.review.dto.ReviewCreateRequest;
import com.example.demo.domain.review.dto.ReviewResponse;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;

import com.example.demo.domain.store.entity.Store;
import com.example.demo.domain.store.repository.StoreRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

	// 하드코딩 유저 ID
	private static final Long HARD_CODED_USER_ID = 1L;

	private final UserRepository userRepository;
	private final StoreRepository storeRepository;
	private final ReviewRepository reviewRepository;
	private final MissionRepository missionRepository;
	private final UserMissionRepository userMissionRepository;



	@Override
	public ReviewResponse createReview(Long storeId, ReviewCreateRequest request) {
		User user = getHardCodedUser();

		Store store = storeRepository.findById(storeId)
			.orElseThrow(() -> new IllegalArgumentException("Store not found. id=" + storeId));

		Review review = Review.builder()
			.user(user)
			.store(store)
			.star(request.getStar())
			.content(request.getContent())
			.build();

		Review saved = reviewRepository.save(review);

		return new ReviewResponse(
			saved.getId(),
			store.getId(),
			user.getId(),
			saved.getStar(),
			saved.getContent()
		);
	}



	@Override
	public UserMissionResponse challengeMission(Long missionId) {
		User user = getHardCodedUser();

		Mission mission = missionRepository.findById(missionId)
			.orElseThrow(() -> new IllegalArgumentException("Mission not found. id=" + missionId));

		// 이미 도전 중이면 예외
		if (userMissionRepository.existsByUserIdAndMissionId(user.getId(), mission.getId())) {
			throw new IllegalStateException("이미 도전 중인 미션입니다.");
		}

		UserMission userMission = UserMission.builder()
			.user(user)
			.mission(mission)
			.status(UserMissionStatus.IN_PROGRESS) // 도전 중
			.earnedPoint(0)
			.build();

		UserMission saved = userMissionRepository.save(userMission);

		return new UserMissionResponse(
			saved.getId(),
			saved.getUser().getId(),
			saved.getMission().getId(),
			saved.getStatus(),
			saved.getEarnedPoint(),
			saved.getCompletedAt()
		);
	}

	private User getHardCodedUser() {
		return userRepository.findById(HARD_CODED_USER_ID)
			.orElseThrow(() ->
				new IllegalStateException("유저를 찾을 수 없습니다. HARD_CODED_USER_ID=" + HARD_CODED_USER_ID));
	}
}
