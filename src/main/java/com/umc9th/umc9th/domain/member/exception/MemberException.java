package com.umc9th.umc9th.domain.member.exception;

import com.umc9th.umc9th.global.apiPayload.code.BaseErrorCode;
import com.umc9th.umc9th.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {

    public MemberException(BaseErrorCode code) {
        super(code);
    }

}
