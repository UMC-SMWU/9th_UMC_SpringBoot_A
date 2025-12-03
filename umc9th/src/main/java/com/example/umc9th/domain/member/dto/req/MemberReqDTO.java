package com.example.umc9th.domain.member.dto.req;

import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            @NotBlank String name,
            @NotNull String email,
            String password,
            @NotNull LocalDate birthDate,
            @NotNull String address,
            @NotNull String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ) {}
}