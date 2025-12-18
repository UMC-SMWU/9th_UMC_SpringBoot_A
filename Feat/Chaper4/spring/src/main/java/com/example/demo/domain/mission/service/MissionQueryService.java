package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.StoreMissionItemView;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.global.paging.PageResponse;
import com.example.demo.global.paging.PageResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

	private final MissionRepository missionRepository;

	public PageResponse<StoreMissionItemView> getStoreMissions(Long storeId, int page) {
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "deadline").and(Sort.by("id")));
		Page<Mission> result = missionRepository.findByStoreId(storeId, pageable);
		return PageResponseConverter.from(result, page, MissionConverter::toStoreMissionItem);
	}
}
