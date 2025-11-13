package com.example.demo.global.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	private final boolean success; // 성공 여부
	private final String code;     // 성공 / 에러 코드
	private final String message;  // 설명 메시지
	private final T data;          // 실제 데이터

	@Builder
	private ApiResponse(boolean success, String code, String message, T data) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	// 성공 메서드
	public static <T> ApiResponse<T> success(SuccessCode successCode, T data) {
		return ApiResponse.<T>builder()
			.success(true)
			.code(successCode.getCode())
			.message(successCode.getMessage())
			.data(data)
			.build();
	}

	// 에러용
	public static <T> ApiResponse<T> error(ErrorCode errorCode) {
		return ApiResponse.<T>builder()
			.success(false)
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.build();
	}


}
