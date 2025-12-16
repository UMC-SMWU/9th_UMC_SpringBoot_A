package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record AddMissionDTO(
            Long userMissionId,
            Long memberId,
            Long missionId,
            LocalDateTime assignedAt
    ) {}


    @Builder
    public record MyMissionPreViewDTO(
            Long userMissionId,
            Long missionId,
            String title,
            String description,
            Integer pointsReward,
            String status,
            LocalDateTime assignedAt
    ) {}

    @Builder
    public record MyMissionPreViewListDTO(
            List<MyMissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}