package com.nova.talentnova.dto;

import com.nova.talentnova.GeneralStatus;
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
    private GeneralStatus status;
    private Long workShiftId;
    private Long departmentId;
    private Long jobPositionId;
}