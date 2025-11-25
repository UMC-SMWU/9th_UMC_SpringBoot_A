package com.umc9th.umc9th.domain.member.converter;

import com.umc9th.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.umc9th.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.umc9th.umc9th.domain.member.entity.Member;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResponseDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResponseDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    //DTO -> Entity
    public static Member toMember(MemberRequestDTO.JoinDTO dto) {
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                // 그대로 받아서 던져주는 것을 확인할 수 있다
                .gender(dto.gender())
                .build();


    }
}
