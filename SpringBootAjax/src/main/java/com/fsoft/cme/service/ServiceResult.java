package com.fsoft.cme.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class ServiceResult {
    private String message;
    private Object data;
    private HttpStatus status;
    private String errorCode;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }


}

