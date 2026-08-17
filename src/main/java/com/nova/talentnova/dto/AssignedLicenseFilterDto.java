package com.nova.talentnova.dto;

import com.nova.talentnova.LicenseAssignmentStatus;
import lombok.Data;

@Data
public class AssignedLicenseFilterDto {
    private Long employeeId;
    private LicenseAssignmentStatus status;
}
