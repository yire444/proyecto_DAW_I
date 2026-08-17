package com.nova.talentnova.dto;

import com.nova.talentnova.LicenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareLicenseResponseDto {

    private Long id;
    private Long companyId;
    private String softwareName;
    private String provider;
    private LicenseType licenseType;
    private Integer totalKeys;
    private Integer availableKeys;
    private LocalDate expirationDate;
}