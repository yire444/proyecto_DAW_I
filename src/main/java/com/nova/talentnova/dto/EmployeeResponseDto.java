package com.nova.talentnova.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeResponseDto {

    private Integer id;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private Character gender;
    private String address;
    private String mobilePhone;
    private String personalEmail;
    private String corporateEmail;

    private String documentTypeName;
    private String documentNumber;


    private String jobPositionName;
    private String departamentName;
    private LocalDate startDate;
    private BigDecimal salary;


    private String contractTypeName;
    private String workShiftName;
    private String insuranceSchemeName;
    private String pensionSchemeName;


    private Boolean status;
    private LocalDateTime createdAt;
}