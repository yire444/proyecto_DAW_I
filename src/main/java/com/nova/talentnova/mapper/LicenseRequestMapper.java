package com.nova.talentnova.mapper;

import com.nova.talentnova.LicenseStatus;
import com.nova.talentnova.dto.LicenseRequestRequestDto;
import com.nova.talentnova.dto.LicenseRequestResponseDto;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.LicenseRequest;
import com.nova.talentnova.model.SoftwareLicense;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LicenseRequestMapper {

    // DTO -> ENTIDAD
    public LicenseRequest toEntity(LicenseRequestRequestDto dto, Employee employee, SoftwareLicense license) {
        if (dto == null) {
            return null;
        }

        LicenseRequest request = new LicenseRequest();
        request.setEmployee(employee);
        request.setLicense(license);
        request.setJustification(dto.getJustification());

        request.setStatus(LicenseStatus.PENDING);
        request.setRequestDate(LocalDateTime.now());
        request.setApprovalDate(null);

        return request;
    }

    // ENTIDAD -> RESPONSE DTO
    public LicenseRequestResponseDto toResponseDto(LicenseRequest request) {
        if (request == null) {
            return null;
        }

        LicenseRequestResponseDto dto = new LicenseRequestResponseDto();
        dto.setId(request.getId());
        dto.setEmployeeId(request.getEmployee().getId());
        dto.setEmployeeFullName(request.getEmployee().getName());
        
        dto.setLicenseId(request.getLicense().getId());
        dto.setSoftwareName(request.getLicense().getSoftwareName());
        dto.setRequestDate(request.getRequestDate());
        dto.setJustification(request.getJustification());
        dto.setStatus(request.getStatus());
        dto.setApprovalDate(request.getApprovalDate());

        return dto;
    }
}