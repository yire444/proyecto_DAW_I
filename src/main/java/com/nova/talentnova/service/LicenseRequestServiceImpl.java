package com.nova.talentnova.service;

import com.nova.talentnova.LicenseStatus;
import com.nova.talentnova.dto.LicenseRequestFilterDto;
import com.nova.talentnova.dto.LicenseRequestRequestDto;
import com.nova.talentnova.dto.LicenseRequestResponseDto;
import com.nova.talentnova.mapper.LicenseRequestMapper;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.LicenseRequest;
import com.nova.talentnova.model.SoftwareLicense;
import com.nova.talentnova.repository.IEmployeeRepository;
import com.nova.talentnova.repository.ILicenseRequestRepository;
import com.nova.talentnova.repository.ISoftwareLicenseRepository;
import com.nova.talentnova.service.ILicenseRequestService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseRequestServiceImpl implements ILicenseRequestService {

    private final ILicenseRequestRepository requestRepo;
    private final ISoftwareLicenseRepository licenseRepo;
    private final IEmployeeRepository employeeRepo;
    private final LicenseRequestMapper requestMap;

    //REGISTRAR
    @Override
    public LicenseRequestResponseDto createRequest(Long employeeId, LicenseRequestRequestDto dto) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + employeeId));

        SoftwareLicense license = licenseRepo.findById(dto.getLicenseId())
                .orElseThrow(() -> new EntityNotFoundException("Licencia no encontrada con ID: " + dto.getLicenseId()));

        LicenseRequest request = requestMap.toEntity(dto, employee, license);
        return requestMap.toResponseDto(requestRepo.save(request));
    }

    //LISTAR POR EMPRESA
    @Override
    public List<LicenseRequestResponseDto> getRequestsByCompany(Long companyId) {
        return requestRepo.findByLicenseCompanyId(companyId).stream()
                .map(requestMap::toResponseDto)
                .toList();
    }

    //FILTRAR
    @Override
    public List<LicenseRequestResponseDto> filterRequests(Long companyId, LicenseRequestFilterDto filter) {
        return requestRepo.filterRequests(
                companyId,
                filter.getId(),
                filter.getEmployeeId(),
                filter.getStatus()
        ).stream()
         .map(requestMap::toResponseDto)
         .toList();
    }

    //BUSCAR POR ID
    @Override
    public LicenseRequestResponseDto getRequestById(Long id) {
        return requestRepo.findById(id)
                .map(requestMap::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada con ID: " + id));
    }

    //ACTUALIZAR
    @Override
    public LicenseRequestResponseDto updateRequestStatus(Long id, LicenseStatus status) {
        LicenseRequest request = requestRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada con ID: " + id));

        request.setStatus(status);
        if (status == LicenseStatus.APPROVED) {
            request.setApprovalDate(LocalDateTime.now());
        }

        return requestMap.toResponseDto(requestRepo.save(request));
    }

    //ELIMINAR
    @Override
    public void deleteRequest(Long id) {
        if (!requestRepo.existsById(id)) {
            throw new EntityNotFoundException("Solicitud no encontrada con ID: " + id);
        }
        requestRepo.deleteById(id);
    }
}