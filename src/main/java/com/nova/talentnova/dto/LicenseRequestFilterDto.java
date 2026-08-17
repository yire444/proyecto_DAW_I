package com.nova.talentnova.dto;

import com.nova.talentnova.LicenseStatus;
import lombok.Data;

@Data
public class LicenseRequestFilterDto {
    private Long id;
    private Long employeeId;
    private LicenseStatus status;
}