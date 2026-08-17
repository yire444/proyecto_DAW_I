package com.nova.talentnova.dto;

import com.nova.talentnova.LicenseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicenseRequestResponseDto {

    private Long id;
    private Long employeeId;
    private String employeeFullName;
    private Long licenseId;
    private String softwareName;
    private LocalDateTime requestDate;
    private String justification;
    private LicenseStatus status;
    private LocalDateTime approvalDate;
}