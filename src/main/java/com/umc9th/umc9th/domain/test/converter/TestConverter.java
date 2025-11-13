package com.umc9th.umc9th.domain.test.converter;

import com.umc9th.umc9th.domain.test.dto.request.TestRequestDTO;
import com.umc9th.umc9th.domain.test.dto.response.TestResponseDTO;

public class TestConverter {

    // 객체 -> DTO
    public static TestResponseDTO.Testing toTestingDTO(String testing){
        return TestResponseDTO.Testing.builder()
                .testing(testing)
                .build();
    }
}
