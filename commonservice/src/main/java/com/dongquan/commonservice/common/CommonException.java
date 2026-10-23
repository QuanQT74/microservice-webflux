package com.dongquan.commonservice.common;

import lombok.Data;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;

@Data
public class CommonException extends RuntimeException{

private final String Code;

private final String message;

private final HttpStatus status;

public CommonException(String code , String message , HttpStatus status){
    super(message);
    this.Code = code;
    this.message = message;
    this.status = status;
}
}
