package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.entity.enums.MissionStatus;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MemberRepository memberRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public MissionResDTO.MyMissionPreViewListDTO getMyInProgressMissions(Long memberId, Integer page) {

        // page는 1 이상
        if (page == null || page < 1) {
            throw new IllegalArgumentException("page는 1 이상이어야 합니다.");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("MEMBER_NOT_FOUND"));

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<UserMission> result =
                userMissionRepository.findAllByMemberAndStatus(member, MissionStatus.IN_PROGRESS, pageRequest);

        return MissionConverter.toMyMissionPreViewListDTO(result);
    }
}