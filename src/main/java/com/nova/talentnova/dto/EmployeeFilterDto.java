package com.nova.talentnova.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFilterDto {
    private Long id;
    private String corporateEmail;
    private Boolean status;
    private Long workShiftId;
    private Long departamentId;
    private Long jobPositionId;
}