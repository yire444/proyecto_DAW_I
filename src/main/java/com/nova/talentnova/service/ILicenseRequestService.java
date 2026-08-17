package com.nova.talentnova.service;

import com.nova.talentnova.LicenseStatus;
import com.nova.talentnova.dto.LicenseRequestFilterDto;
import com.nova.talentnova.dto.LicenseRequestRequestDto;
import com.nova.talentnova.dto.LicenseRequestResponseDto;

import java.util.List;

public interface ILicenseRequestService {

    //REGISTRAR LICENCIA
    LicenseRequestResponseDto createRequest(Long employeeId, LicenseRequestRequestDto dto);

    //LISTAR POR EMPRESA
    List<LicenseRequestResponseDto> getRequestsByCompany(Long companyId);

    //FILTRAR
    List<LicenseRequestResponseDto> filterRequests(Long companyId, LicenseRequestFilterDto filter);

    //BUSCAR POR ID
    LicenseRequestResponseDto getRequestById(Long id);

    //ACTUALIZAR
    LicenseRequestResponseDto updateRequestStatus(Long id, LicenseStatus status);

    //ELIMINAR
    void deleteRequest(Long id);
}