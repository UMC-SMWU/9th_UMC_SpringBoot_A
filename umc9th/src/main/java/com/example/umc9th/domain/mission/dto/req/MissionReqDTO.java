package com.example.umc9th.domain.mission.dto.req;

public class MissionReqDTO {

    public record AddMissionDTO(
            Long memberId,
            Long missionId
    ) {}
}