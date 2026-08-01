package com.nova.talentnova.dto;

import lombok.Data;

@Data
public class EmployeeFilterDto {
    private Integer id;
    private String corporateEmail;
    private Boolean status;
    private Integer workShiftId;
    private Integer departamentId;
    private Integer jobPositionId;
}