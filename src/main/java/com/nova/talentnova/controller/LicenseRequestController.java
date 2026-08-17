package com.nova.talentnova.controller;

import com.nova.talentnova.LicenseStatus;
import com.nova.talentnova.dto.LicenseRequestFilterDto;
import com.nova.talentnova.dto.LicenseRequestRequestDto;
import com.nova.talentnova.dto.LicenseRequestResponseDto;
import com.nova.talentnova.service.ILicenseRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/license-request")
@RequiredArgsConstructor
public class LicenseRequestController {

    private final ILicenseRequestService requestService;

    //REGISTRAR
    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<LicenseRequestResponseDto> createRequest(
            @PathVariable Long employeeId,
            @Valid @RequestBody LicenseRequestRequestDto dto) {
        LicenseRequestResponseDto newRequest = requestService.createRequest(employeeId, dto);
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }

    //LISTAR POR EMPRESA
    @ResponseBody
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<LicenseRequestResponseDto>> getRequestsByCompany(@PathVariable Long companyId) {
        List<LicenseRequestResponseDto> requests = requestService.getRequestsByCompany(companyId);
        return ResponseEntity.ok(requests);
    }

    //FILTRAR
    @GetMapping("/company/{companyId}/filter")
    public ResponseEntity<List<LicenseRequestResponseDto>> filterRequests(
            @PathVariable Long companyId,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) LicenseStatus status) {

        LicenseRequestFilterDto filter = new LicenseRequestFilterDto();
        filter.setId(id);
        filter.setEmployeeId(employeeId);
        filter.setStatus(status);

        List<LicenseRequestResponseDto> requests = requestService.filterRequests(companyId, filter);
        return ResponseEntity.ok(requests);
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<LicenseRequestResponseDto> getRequestById(@PathVariable Long id) {
        LicenseRequestResponseDto request = requestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }

    //ACTUALIZAR
    @PatchMapping("/{id}/status")
    public ResponseEntity<LicenseRequestResponseDto> updateRequestStatus(
            @PathVariable Long id,
            @RequestParam LicenseStatus status) {
        LicenseRequestResponseDto updated = requestService.updateRequestStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}