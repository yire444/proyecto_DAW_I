package com.nova.talentnova.service;

import com.nova.talentnova.LicenseAssignmentStatus;
import com.nova.talentnova.dto.AssignedLicenseFilterDto;
import com.nova.talentnova.dto.AssignedLicenseRequestDto;
import com.nova.talentnova.dto.AssignedLicenseResponseDto;
import com.nova.talentnova.mapper.AssignedLicenseMapper;
import com.nova.talentnova.model.AssignedLicense;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.SoftwareLicense;
import com.nova.talentnova.repository.IAssignedLicenseRepository;
import com.nova.talentnova.repository.IEmployeeRepository;
import com.nova.talentnova.repository.ISoftwareLicenseRepository;
import com.nova.talentnova.service.IAssignedLicenseService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignedLicenseServiceImpl implements IAssignedLicenseService {

    private final IAssignedLicenseRepository assignedRepo;
    private final IEmployeeRepository employeeRepo;
    private final ISoftwareLicenseRepository licenseRepo;
    private final AssignedLicenseMapper assignedMapper;

    //REGISTRAR
    @Override
    public AssignedLicenseResponseDto assignLicense(AssignedLicenseRequestDto dto) {
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + dto.getEmployeeId()));

        SoftwareLicense license = licenseRepo.findById(dto.getLicenseId())
                .orElseThrow(() -> new EntityNotFoundException("Licencia no encontrada con ID: " + dto.getLicenseId()));

        AssignedLicense assignedLicense = assignedMapper.toEntity(dto, employee, license);
        return assignedMapper.toResponseDto(assignedRepo.save(assignedLicense));
    }

    //LISTAR POR EMPRESA
    @Override
    public List<AssignedLicenseResponseDto> getAssignedLicensesByCompany(Long companyId) {
        return assignedRepo.findBySoftwareLicenseCompanyId(companyId).stream()
                .map(assignedMapper::toResponseDto)
                .toList();
    }

    //FILTRAR
    @Override
    public List<AssignedLicenseResponseDto> filterAssignedLicenses(Long companyId, AssignedLicenseFilterDto filter) {
        return assignedRepo.filterAssignedLicenses(
                companyId,
                filter.getEmployeeId(),
                filter.getStatus()
        ).stream()
         .map(assignedMapper::toResponseDto)
         .toList();
    }

    //BUSCAR POR ID
    @Override
    public AssignedLicenseResponseDto getAssignedLicenseById(Long id) {
        AssignedLicense assigned = assignedRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asignación no encontrada con ID: " + id));
        return assignedMapper.toResponseDto(assigned);
    }

    //ACTUALIZAR
    @Override
    public AssignedLicenseResponseDto updateStatus(Long id, LicenseAssignmentStatus status) {
        AssignedLicense assigned = assignedRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asignación no encontrada con ID: " + id));

        assigned.setStatus(status);

        if (status == LicenseAssignmentStatus.REVOKED) {
            assigned.setRevokedDate(LocalDate.now());
        }

        return assignedMapper.toResponseDto(assignedRepo.save(assigned));
    }

    //ELIMINAR
    @Override
    public void deleteAssignedLicense(Long id) {
        if (!assignedRepo.existsById(id)) {
            throw new EntityNotFoundException("Asignación no encontrada con ID: " + id);
        }
        assignedRepo.deleteById(id);
    }
}