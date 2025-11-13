package com.umc9th.umc9th.domain.test.exception;

import com.umc9th.umc9th.global.apiPayload.code.BaseErrorCode;
import com.umc9th.umc9th.global.apiPayload.exception.GeneralException;

// 도메인 Exception
public class TestException extends GeneralException {
    public TestException(BaseErrorCode code){
        super(code);
    }
}
