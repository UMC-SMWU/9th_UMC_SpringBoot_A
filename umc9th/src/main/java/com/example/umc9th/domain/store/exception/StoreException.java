package com.example.umc9th.domain.store.exception;

import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {

    public StoreException(StoreErrorCode errorCode) {
        super(errorCode);
    }
}