package com.nova.talentnova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private String token;
    private String type = "Bearer";
    private String corporateEmail;
    private String systemRole;
    private Integer employeeId;
}