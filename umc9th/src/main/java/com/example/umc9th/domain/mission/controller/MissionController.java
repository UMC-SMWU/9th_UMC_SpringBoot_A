package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/add")
    public ApiResponse<MissionResDTO.AddMissionDTO> addMission(
            @RequestBody MissionReqDTO.AddMissionDTO dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode .CREATED,
                missionService.addMission(dto));
    }
}