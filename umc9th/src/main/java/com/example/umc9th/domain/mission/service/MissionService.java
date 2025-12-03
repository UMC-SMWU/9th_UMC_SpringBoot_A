package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final MemberRepository memberRepository;

    public MissionResDTO.AddMissionDTO addMission(MissionReqDTO.AddMissionDTO dto) {

        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new RuntimeException("MEMBER_NOT_FOUND"));

        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new RuntimeException("MISSION_NOT_FOUND"));

        UserMission userMission = UserMission.builder()
                .member(member)
                .mission(mission)
                .startedAt(LocalDateTime.now())
                .build();

        userMissionRepository.save(userMission);

        return MissionResDTO.AddMissionDTO.builder()
                .userMissionId(userMission.getId())
                .memberId(member.getId())
                .missionId(mission.getId())
                .startedAt(userMission.getStartedAt())
                .build();
    }
}