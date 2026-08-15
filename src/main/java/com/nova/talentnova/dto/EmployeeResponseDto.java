package com.nova.talentnova.dto;

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
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private Character gender;
    private String address;
    private String mobilePhone;
    private String personalEmail;
    private String corporateEmail;
    private String documentNumber;
    private LocalDate startDate;
    private BigDecimal salary;
    private Boolean status;
    private LocalDateTime createdAt;

    //CATÁLOGOS
    private Long documentTypeId;
    private String documentTypeName;

    private Long jobPositionId;
    private String jobPositionName;

    private Long departamentId;
    private String departamentName;

    private Long contractTypeId;
    private String contractTypeName;

    private Long workShiftId;
    private String workShiftName;

    private Long insuranceSchemeId;
    private String insuranceSchemeName;

    private Long pensionSchemeId;
    private String pensionSchemeName;
}