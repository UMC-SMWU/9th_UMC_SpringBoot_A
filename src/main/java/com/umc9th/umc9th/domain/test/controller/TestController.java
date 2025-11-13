package com.umc9th.umc9th.domain.test.controller;

import com.umc9th.umc9th.domain.test.converter.TestConverter;
import com.umc9th.umc9th.domain.test.dto.response.TestResponseDTO;
import com.umc9th.umc9th.global.apiPayload.ApiResponse;
import com.umc9th.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    @GetMapping("/test")
    public ApiResponse<TestResponseDTO.Testing> test() throws Exception{
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(
                code, TestConverter.toTestingDTO("This is Test!")
        );
    }
}
