package com.example.demo.global.exception;

import com.example.demo.global.api.ErrorCode;

public class InvalidPageException extends BusinessException {
	public InvalidPageException() {
		super(ErrorCode.INVALID_PAGE);
	}
}
