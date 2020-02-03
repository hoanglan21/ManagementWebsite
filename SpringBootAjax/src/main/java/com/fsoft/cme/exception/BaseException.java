package com.fsoft.cme.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public final String errorCode;
    public final String errorMessage;
    public final HttpStatus httpStatus;


    public BaseException(final ExceptionCode errorCode1) {
        errorCode = errorCode1.getErrorCode();
        errorMessage = errorCode1.getErrorMessage();
        httpStatus = errorCode1.getHttpStatus();
    }
}
