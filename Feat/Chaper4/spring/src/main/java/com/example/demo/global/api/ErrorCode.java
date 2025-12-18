package com.example.demo.global.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E500", "서버 내부 오류가 발생했습니다."),
	VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "E400", "요청 값이 유효하지 않습니다."),
	INVALID_PAGE(HttpStatus.BAD_REQUEST, "E401", "page는 1 이상이어야 합니다."),

	DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "E402", "이미 사용 중인 이메일입니다."),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "E403", "이메일 또는 비밀번호가 올바르지 않습니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "E404", "인증이 필요합니다."),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "E405", "토큰이 유효하지 않습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}
}
