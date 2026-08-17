package com.nova.talentnova.dto;

import com.nova.talentnova.LicenseType;
import lombok.Data;

@Data
public class SoftwareLicenseFilterDto {
    private Long id;
    private String softwareName;
    private String provider;
    private LicenseType licenseType;
}