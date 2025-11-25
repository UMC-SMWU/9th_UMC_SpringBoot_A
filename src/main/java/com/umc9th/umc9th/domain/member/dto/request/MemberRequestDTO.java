package com.umc9th.umc9th.domain.member.dto.request;

import com.umc9th.umc9th.domain.member.enums.Address;
import com.umc9th.umc9th.domain.member.enums.Gender;
import com.umc9th.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberRequestDTO {
    // 회원가입
    public record JoinDTO(
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            Address address,
            @NotNull
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
