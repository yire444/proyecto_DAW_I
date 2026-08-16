package com.nova.talentnova.dto;

import lombok.Data;

@Data
public class EmergencyContactResponseDto {
    private Long id;
    private Long employeeId;
    private String name;
    private String relationship;
    private String mobilePhone;
    private String address;
}