package com.nova.talentnova.dto;

import lombok.Data;

@Data
public class EmergencyContactResponseDto {

    private Integer id;
    private Integer employeeId;
    private String name;
    private String relationShip;
    private String mobilePhone;
    private String address;
}