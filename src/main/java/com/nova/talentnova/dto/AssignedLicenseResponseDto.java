package com.nova.talentnova.dto;

import com.nova.talentnova.LicenseAssignmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignedLicenseResponseDto {
    private Long id;
    private String employeeFullName;
    private String softwareName;
    private LocalDate assignedDate;
    private LocalDate revokedDate;
    private LicenseAssignmentStatus status;
}
