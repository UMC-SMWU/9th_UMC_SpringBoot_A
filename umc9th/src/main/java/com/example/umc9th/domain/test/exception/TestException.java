package com.example.umc9th.domain.test.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class TestException extends RuntimeException {   // ★ 여기 RuntimeException!

    private final BaseErrorCode code;

    public TestException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}