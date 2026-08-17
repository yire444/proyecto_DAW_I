package com.nova.talentnova.mapper;

import com.nova.talentnova.LicenseAssignmentStatus;
import com.nova.talentnova.dto.AssignedLicenseRequestDto;
import com.nova.talentnova.dto.AssignedLicenseResponseDto;
import com.nova.talentnova.model.AssignedLicense;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.SoftwareLicense;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AssignedLicenseMapper {

    // DTO -> ENTIDAD
    public AssignedLicense toEntity(AssignedLicenseRequestDto dto, Employee employee, SoftwareLicense softwareLicense) {
        if (dto == null) {
            return null;
        }

        AssignedLicense assignedLicense = new AssignedLicense();
        assignedLicense.setEmployee(employee);
        assignedLicense.setSoftwareLicense(softwareLicense);
        assignedLicense.setAssignedDate(LocalDate.now());
        assignedLicense.setStatus(LicenseAssignmentStatus.ACTIVE);
        assignedLicense.setRevokedDate(null);

        return assignedLicense;
    }

    // ENTIDAD -> DTO
    public AssignedLicenseResponseDto toResponseDto(AssignedLicense assignedLicense) {
        if (assignedLicense == null) {
            return null;
        }

        AssignedLicenseResponseDto dto = new AssignedLicenseResponseDto();
        dto.setId(assignedLicense.getId());
        dto.setEmployeeFullName(assignedLicense.getEmployee().getName());
        dto.setSoftwareName(assignedLicense.getSoftwareLicense().getSoftwareName());
        dto.setAssignedDate(assignedLicense.getAssignedDate());
        dto.setRevokedDate(assignedLicense.getRevokedDate());
        dto.setStatus(assignedLicense.getStatus());

        return dto;
    }
}