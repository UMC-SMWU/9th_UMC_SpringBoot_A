package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    public record AddMissionDTO(
            Long userMissionId,
            Long memberId,
            Long missionId,
            LocalDateTime startedAt
    ) {}
}