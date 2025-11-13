package com.umc9th.umc9th.domain.test.dto.response;

import lombok.Builder;
import lombok.Getter;

public class TestResponseDTO {

    @Builder
    @Getter
    public static class Testing{
        private String testing;
    }

    @Builder
    @Getter
    public static class Exception{
        private String testString;
    }
}
