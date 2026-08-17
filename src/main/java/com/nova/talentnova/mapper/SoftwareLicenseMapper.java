package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.SoftwareLicenseRequestDto;
import com.nova.talentnova.dto.SoftwareLicenseResponseDto;
import com.nova.talentnova.model.SoftwareLicense;
import org.springframework.stereotype.Component;

@Component
public class SoftwareLicenseMapper {

    // DTO -> ENTIDAD
    public SoftwareLicense toEntity(SoftwareLicenseRequestDto dto, Long companyId) {
        if (dto == null) {return null;}

        SoftwareLicense license = new SoftwareLicense();
        license.setCompanyId(companyId);
        license.setSoftwareName(dto.getSoftwareName());
        license.setProvider(dto.getProvider());
        license.setLicenseType(dto.getLicenseType());
        license.setTotalKeys(dto.getTotalKeys());

        license.setAvailableKeys(dto.getTotalKeys());
        
        license.setExpirationDate(dto.getExpirationDate());

        return license;
    }

    // ENTIDAD -> DTO
    public SoftwareLicenseResponseDto toResponseDto(SoftwareLicense license) {
        if (license == null) {return null;}

        SoftwareLicenseResponseDto dto = new SoftwareLicenseResponseDto();
        dto.setId(license.getId());
        dto.setCompanyId(license.getCompanyId());
        dto.setSoftwareName(license.getSoftwareName());
        dto.setProvider(license.getProvider());
        dto.setLicenseType(license.getLicenseType());
        dto.setTotalKeys(license.getTotalKeys());
        dto.setAvailableKeys(license.getAvailableKeys());
        dto.setExpirationDate(license.getExpirationDate());

        return dto;
    }
}