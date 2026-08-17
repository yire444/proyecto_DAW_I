package com.nova.talentnova.service;

import com.nova.talentnova.LicenseAssignmentStatus;
import com.nova.talentnova.dto.AssignedLicenseFilterDto;
import com.nova.talentnova.dto.AssignedLicenseRequestDto;
import com.nova.talentnova.dto.AssignedLicenseResponseDto;

import java.util.List;

public interface IAssignedLicenseService {

    //REGISTRAR
    AssignedLicenseResponseDto assignLicense(AssignedLicenseRequestDto dto);

    //LISTAR POR EMPRESA
    List<AssignedLicenseResponseDto> getAssignedLicensesByCompany(Long companyId);

    //FILTRAR
    List<AssignedLicenseResponseDto> filterAssignedLicenses(Long companyId, AssignedLicenseFilterDto filter);

    //BUSCAR POR ID
    AssignedLicenseResponseDto getAssignedLicenseById(Long id);

    //ACTUALIZAR
    AssignedLicenseResponseDto updateStatus(Long id, LicenseAssignmentStatus status);

    //ELIMINAR
    void deleteAssignedLicense(Long id);
}