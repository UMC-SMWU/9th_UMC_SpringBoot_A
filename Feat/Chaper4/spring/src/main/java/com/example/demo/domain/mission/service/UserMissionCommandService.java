package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.constant.UserMissionStatus;
import com.example.demo.domain.mission.dto.UserMissionItemView;
import com.example.demo.domain.mission.entity.UserMission;
import com.example.demo.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserMissionCommandService {

	private final UserMissionRepository userMissionRepository;

	@Transactional
	public UserMissionItemView complete(Long userId, Long userMissionId) {
		UserMission um = userMissionRepository.findWithAllById(userMissionId)
			.orElseThrow(() -> new IllegalArgumentException("UserMission not found. id=" + userMissionId));

		if (!um.getUser().getId().equals(userId)) {
			throw new IllegalStateException("내 미션이 아닙니다.");
		}

		if (um.getStatus() != UserMissionStatus.IN_PROGRESS) {
			throw new IllegalStateException("진행중(IN_PROGRESS) 미션만 완료 처리할 수 있습니다.");
		}

		int earned = um.getMission().getBasePoint();
		um.complete(earned, LocalDateTime.now());

		// “변경된 상태 조회까지” 충족: 변경 직후 값으로 응답 DTO 생성(Builder)
		return UserMissionItemView.builder()
			.userMissionId(um.getId())
			.status(um.getStatus())              // COMPLETED
			.createdAt(um.getCreatedAt())
			.completedAt(um.getCompletedAt())    // now
			.missionId(um.getMission().getId())
			.title(um.getMission().getTitle())
			.deadline(um.getMission().getDeadline())
			.storeId(um.getMission().getStore().getId())
			.storeName(um.getMission().getStore().getName())
			.build();
	}
}
