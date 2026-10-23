package com.dongquan.commonservice.common;

import com.networknt.schema.ValidationMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class ValidationException  extends RuntimeException{
    private final String code;

    private final Map<String,String> messageMap;

    private final HttpStatus status;

    public ValidationException(String code , Map<String,String> messageMap , HttpStatus status){
        this.code = code;
        this.messageMap = messageMap;
        this.status = status;
    }
}
