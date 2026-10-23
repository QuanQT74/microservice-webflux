package com.dongquan.commonservice.advide;

import com.dongquan.commonservice.common.CommonException;
import com.dongquan.commonservice.common.ErrorMessage;
import com.dongquan.commonservice.common.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionAdvide {
    @ExceptionHandler
    public ResponseEntity<ErrorMessage>handleException(Exception ex){
        log.error("Unknown internal server error :" + ex.getMessage());
        log.error("Exception class : " + ex.getClass());
        log.error("Exception cause :"+ ex.getCause());
        return new ResponseEntity<>(new ErrorMessage("99999", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleCommomExeption(CommonException ex){
        log.error(String.format("Common error: %s %s %s ",ex.getCode(),ex.getMessage(),ex.getStatus()));
        return new ResponseEntity<>(new ErrorMessage(ex.getCode(), ex.getMessage(), ex.getStatus()),ex.getStatus());
    }
    @ExceptionHandler
    public ResponseEntity<Map<String,String>> handleValidationException(ValidationException exception){
        log.error("ValidationException error");
        return new ResponseEntity<>(exception.getMessageMap(),exception.getStatus());
    }
}
