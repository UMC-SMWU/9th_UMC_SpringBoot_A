package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    // 리뷰 생성
    CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰 생성 성공"),

    // 리뷰(목록) 조회
    FOUND(HttpStatus.OK, "REVIEW200_1", "리뷰 조회 성공"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}