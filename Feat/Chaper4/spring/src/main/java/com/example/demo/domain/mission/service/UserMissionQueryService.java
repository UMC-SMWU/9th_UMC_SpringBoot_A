package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.converter.UserMissionConverter;
import com.example.demo.domain.mission.repository.UserMissionRepository;
import com.example.demo.dto.UserMissionItemDto;
import com.example.demo.domain.mission.dto.UserMissionItemView;
import com.example.demo.global.paging.PageResponse;
import com.example.demo.global.paging.PageResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMissionQueryService {

	private final UserMissionRepository userMissionRepository;

	public PageResponse<UserMissionItemView> getMyInProgress(Long userId, int page) {
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
		Page<UserMissionItemDto> result = userMissionRepository.findPending(userId, pageable);
		return PageResponseConverter.from(result, page, UserMissionConverter::toView);
	}
}
