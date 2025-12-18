package com.example.demo.global.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessCode {

	// 공통
	CREATE_SUCCESS(HttpStatus.CREATED, "S001", "생성 성공"),
	READ_SUCCESS(HttpStatus.OK, "S002", "조회 성공"),
	UPDATE_SUCCESS(HttpStatus.OK, "S003", "수정 성공"),
	DELETE_SUCCESS(HttpStatus.OK, "S004", "삭제 성공"),

	// 내 리뷰 조회 전용 코드
	GET_MY_REVIEWS_SUCCESS(HttpStatus.OK, "R001", "내 리뷰 목록 조회 성공"),

	CREATE_REVIEW_SUCCESS(HttpStatus.CREATED, "S202", "리뷰 등록 성공"),
	CHALLENGE_MISSION_SUCCESS(HttpStatus.CREATED, "S204", "미션 도전 성공"),
	SIGNUP_SUCCESS(HttpStatus.CREATED, "A001", "회원가입 성공"),
	LOGIN_SUCCESS(HttpStatus.OK, "A002", "로그인 성공"),
	LOGOUT_SUCCESS(HttpStatus.OK, "A003", "로그아웃 성공");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;


	SuccessCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}
}
