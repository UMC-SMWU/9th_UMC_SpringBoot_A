package com.umc9th.umc9th.global.apiPayload.exception;

import com.umc9th.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 프로젝트 Exception
@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
}
