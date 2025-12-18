package com.example.demo.domain.auth.exception;

import com.example.demo.global.api.ErrorCode;
import com.example.demo.global.exception.BusinessException;

public class AuthException extends BusinessException {
	public AuthException(ErrorCode errorCode) {
		super(errorCode);
	}
}
