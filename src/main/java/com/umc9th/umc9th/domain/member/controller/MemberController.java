package com.umc9th.umc9th.domain.member.controller;

import com.umc9th.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.umc9th.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.umc9th.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.umc9th.umc9th.domain.member.service.command.MemberCommandService;
import com.umc9th.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberCommandService memberCommandService;

    // 회원가입 - member 저장
    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberRequestDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signUp(dto));
    }


}
