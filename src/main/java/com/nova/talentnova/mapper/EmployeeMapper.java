package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.EmployeeRequestDto;
import com.nova.talentnova.dto.EmployeeResponseDto;
import com.nova.talentnova.model.*;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setLastname(dto.getLastname());
        employee.setBirthdate(dto.getBirthdate());
        employee.setGender(dto.getGender());
        employee.setAddress(dto.getAddress());
        employee.setMobilePhone(dto.getMobilePhone());
        employee.setPersonalEmail(dto.getPersonalEmail());
        employee.setDocumentNumber(dto.getDocumentNumber());
        employee.setStartDate(dto.getStartDate());
        employee.setSalary(dto.getSalary());

        return employee;
    }

    public static EmployeeResponseDto toResponseDto(Employee entity) {
        if (entity == null) {
            return null;
        }

        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLastname(entity.getLastname());
        dto.setBirthdate(entity.getBirthdate());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setMobilePhone(entity.getMobilePhone());
        dto.setPersonalEmail(entity.getPersonalEmail());
        dto.setCorporateEmail(entity.getCorporateEmail());
        dto.setDocumentNumber(entity.getDocumentNumber());
        dto.setStartDate(entity.getStartDate());
        dto.setSalary(entity.getSalary());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getDocumentType() != null) dto.setDocumentTypeName(entity.getDocumentType().getName());
        if (entity.getJobPosition() != null) dto.setJobPositionName(entity.getJobPosition().getName());
        if (entity.getDepartament() != null) dto.setDepartamentName(entity.getDepartament().getName());
        if (entity.getContractType() != null) dto.setContractTypeName(entity.getContractType().getName());
        if (entity.getWorkShift() != null) dto.setWorkShiftName(entity.getWorkShift().getName());
        if (entity.getInsuranceScheme() != null) dto.setInsuranceSchemeName(entity.getInsuranceScheme().getName());
        if (entity.getPensionScheme() != null) dto.setPensionSchemeName(entity.getPensionScheme().getName());

        return dto;
    }
}