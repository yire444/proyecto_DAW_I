package com.nova.talentnova.dto;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.SystemRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private Long companyId;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private String gender;
    private String address;
    private String mobilePhone;
    private String personalEmail;
    private String corporateEmail;

    private String documentTypeName;
    private String documentNumber;
    private String jobPositionName;
    private String departmentName;
    private LocalDate startDate;
    private BigDecimal salary;
    private String contractTypeName;
    private String workShiftName;
    private String insuranceSchemeName;
    private String pensionSchemeName;

    private GeneralStatus status;
    private SystemRole systemRole;
    private LocalDateTime createdAt;
}