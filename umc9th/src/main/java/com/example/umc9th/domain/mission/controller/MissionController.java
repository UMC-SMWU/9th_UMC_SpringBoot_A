package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/add")
    public ApiResponse<MissionResDTO.AddMissionDTO> addMission(
            @RequestBody MissionReqDTO.AddMissionDTO dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode .CREATED,
                missionService.addMission(dto));
    }

    @GetMapping("/missions/me/in-progress")
    public ApiResponse<MissionResDTO.MyMissionPreViewListDTO> getMyInProgressMissions(
            @RequestParam Long memberId,
            @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getMyInProgressMissions(memberId, page)
        );
    }
}