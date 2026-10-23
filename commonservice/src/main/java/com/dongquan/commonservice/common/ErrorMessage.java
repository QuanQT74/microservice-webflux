package com.dongquan.commonservice.common;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage implements Serializable {
    private String code;

    private String message;

    private HttpStatus status;



}
