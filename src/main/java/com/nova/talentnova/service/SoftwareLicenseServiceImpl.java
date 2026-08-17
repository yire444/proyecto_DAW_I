package com.nova.talentnova.service;

import com.nova.talentnova.dto.SoftwareLicenseFilterDto;
import com.nova.talentnova.dto.SoftwareLicenseRequestDto;
import com.nova.talentnova.dto.SoftwareLicenseResponseDto;
import com.nova.talentnova.mapper.SoftwareLicenseMapper;
import com.nova.talentnova.model.SoftwareLicense;
import com.nova.talentnova.repository.ISoftwareLicenseRepository;
import com.nova.talentnova.service.ISoftwareLicenseService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoftwareLicenseServiceImpl implements ISoftwareLicenseService {

    private final ISoftwareLicenseRepository licenseRepo;
    private final SoftwareLicenseMapper licenseMap;

    //REGISTRAR
    @Override
    public SoftwareLicenseResponseDto createLicense(Long companyId, SoftwareLicenseRequestDto dto) {
        SoftwareLicense license = licenseMap.toEntity(dto, companyId);
        SoftwareLicense savedLicense = licenseRepo.save(license);
        return licenseMap.toResponseDto(savedLicense);
    }

    //LISTAR LICENCIAS POR EMPRESA
    @Override
    public List<SoftwareLicenseResponseDto> getLicensesByCompany(Long companyId) {
        return licenseRepo.findByCompanyId(companyId).stream()
                .map(licenseMap::toResponseDto)
                .toList();
    }

    //FILTRAR
    @Override
    public List<SoftwareLicenseResponseDto> filterLicenses(Long companyId, SoftwareLicenseFilterDto filter) {
        return licenseRepo.filterLicenses(
                companyId,
                filter.getId(),
                filter.getSoftwareName(),
                filter.getProvider(),
                filter.getLicenseType()
        ).stream()
         .map(licenseMap::toResponseDto)
         .toList();
    }

    //BUSCAR POR ID
    @Override
    public SoftwareLicenseResponseDto getLicenseById(Long id) {
        SoftwareLicense license = licenseRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Licencia de software no encontrada con ID: " + id));
        return licenseMap.toResponseDto(license);
    }

    //ACTUALIZAR LICENCIA
    @Override
    public SoftwareLicenseResponseDto updateLicense(Long id, SoftwareLicenseRequestDto dto) {
        SoftwareLicense existingLicense = licenseRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Licencia de software no encontrada con ID: " + id));

        int diff = dto.getTotalKeys() - existingLicense.getTotalKeys();
        int newAvailableKeys = existingLicense.getAvailableKeys() + diff;

        if (newAvailableKeys < 0) {
            throw new IllegalArgumentException("No puedes reducir el total de llaves por debajo de las que ya están asignadas.");
        }

        existingLicense.setSoftwareName(dto.getSoftwareName());
        existingLicense.setProvider(dto.getProvider());
        existingLicense.setLicenseType(dto.getLicenseType());
        existingLicense.setTotalKeys(dto.getTotalKeys());
        existingLicense.setAvailableKeys(newAvailableKeys);
        existingLicense.setExpirationDate(dto.getExpirationDate());

        SoftwareLicense updatedLicense = licenseRepo.save(existingLicense);
        return licenseMap.toResponseDto(updatedLicense);
    }

    //ELIMINAR LICENCIA
    @Override
    public void deleteLicense(Long id) {
        if (!licenseRepo.existsById(id)) {
            throw new EntityNotFoundException("Licencia de software no encontrada con ID: " + id);
        }
        licenseRepo.deleteById(id);
    }
}