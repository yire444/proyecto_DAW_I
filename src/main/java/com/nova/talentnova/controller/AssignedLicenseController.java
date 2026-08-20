package com.nova.talentnova.controller;

import com.nova.talentnova.LicenseAssignmentStatus;
import com.nova.talentnova.dto.AssignedLicenseFilterDto;
import com.nova.talentnova.dto.AssignedLicenseRequestDto;
import com.nova.talentnova.dto.AssignedLicenseResponseDto;
import com.nova.talentnova.service.IAssignedLicenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assigned-license")
@RequiredArgsConstructor
public class AssignedLicenseController {

    private final IAssignedLicenseService assignedService;

    //ASIGNAR LICENCIA
    @PostMapping
    public ResponseEntity<AssignedLicenseResponseDto> assignLicense(
            @Valid @RequestBody AssignedLicenseRequestDto dto) {
        AssignedLicenseResponseDto created = assignedService.assignLicense(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //LISTAR ASIGNACIONES POR EMPRESA
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<AssignedLicenseResponseDto>> getAssignedLicensesByCompany(@PathVariable Long companyId) {
        List<AssignedLicenseResponseDto> list = assignedService.getAssignedLicensesByCompany(companyId);
        return ResponseEntity.ok(list);
    }

    //FILTRAR
    @GetMapping("/company/{companyId}/filter")
    public ResponseEntity<List<AssignedLicenseResponseDto>> filterAssignedLicenses(
            @PathVariable Long companyId,
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) LicenseAssignmentStatus status) {

        AssignedLicenseFilterDto filter = new AssignedLicenseFilterDto();
        filter.setEmployeeId(employeeId);
        filter.setStatus(status);

        List<AssignedLicenseResponseDto> list = assignedService.filterAssignedLicenses(companyId, filter);
        return ResponseEntity.ok(list);
    }

    //BUCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<AssignedLicenseResponseDto> getAssignedLicenseById(@PathVariable Long id) {
        AssignedLicenseResponseDto response = assignedService.getAssignedLicenseById(id);
        return ResponseEntity.ok(response);
    }

    //ACTUALIZAR EL ESTADO
    @PatchMapping("/{id}/status")
    public ResponseEntity<AssignedLicenseResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam LicenseAssignmentStatus status) {
        AssignedLicenseResponseDto updated = assignedService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignedLicense(@PathVariable Long id) {
        assignedService.deleteAssignedLicense(id);
        return ResponseEntity.noContent().build();
    }
}