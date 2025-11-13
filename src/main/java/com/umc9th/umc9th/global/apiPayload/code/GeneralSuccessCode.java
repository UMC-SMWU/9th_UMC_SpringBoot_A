package com.umc9th.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseErrorCode{

    OK(HttpStatus.OK, "COMMON200_1", "성공한 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
