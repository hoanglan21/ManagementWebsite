package com.fsoft.cme.exception;

import com.fsoft.cme.service.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{



    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ServiceResult> springHandler(BaseException exception) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setMessage(exception.errorMessage);
        serviceResult.setStatus(exception.httpStatus);
        serviceResult.setErrorCode(exception.errorCode);
        return new ResponseEntity<>(serviceResult, exception.httpStatus);
    }


}
