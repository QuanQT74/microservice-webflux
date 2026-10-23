package com.dongquan.commonservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {

    private Long id;

    private String email;

    private String status;

    private String name;

    private Double initialBalance;

    private String role;
}