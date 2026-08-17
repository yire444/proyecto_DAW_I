package com.nova.talentnova.service;

import com.nova.talentnova.dto.SoftwareLicenseFilterDto;
import com.nova.talentnova.dto.SoftwareLicenseRequestDto;
import com.nova.talentnova.dto.SoftwareLicenseResponseDto;

import java.util.List;

public interface ISoftwareLicenseService {

    // REGISTRAR
    SoftwareLicenseResponseDto createLicense(Long companyId, SoftwareLicenseRequestDto dto);

    // LISTAR LICENCIAS POR EMPRESA
    List<SoftwareLicenseResponseDto> getLicensesByCompany(Long companyId);

    // FILTRAR
    List<SoftwareLicenseResponseDto> filterLicenses(Long companyId, SoftwareLicenseFilterDto filter);

    // BUSCAR POR ID
    SoftwareLicenseResponseDto getLicenseById(Long id);

    // ACTUALIZAR
    SoftwareLicenseResponseDto updateLicense(Long id, SoftwareLicenseRequestDto dto);

    // ELIMINAR
    void deleteLicense(Long id);
}