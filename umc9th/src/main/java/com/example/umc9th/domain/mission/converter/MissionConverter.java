package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static MissionResDTO.MyMissionPreViewListDTO toMyMissionPreViewListDTO(Page<UserMission> result) {
        return MissionResDTO.MyMissionPreViewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMyMissionPreViewDTO)
                        .toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MyMissionPreViewDTO toMyMissionPreViewDTO(UserMission userMission) {
        return MissionResDTO.MyMissionPreViewDTO.builder()
                .userMissionId(userMission.getUserMissionsId())
                .missionId(userMission.getMission().getId())
                .title(userMission.getMission().getTitle())
                .description(userMission.getMission().getDescription())
                .pointsReward(userMission.getMission().getPointsReward())
                .status(userMission.getStatus().name())
                .assignedAt(userMission.getAssignedAt())
                .build();
    }
}