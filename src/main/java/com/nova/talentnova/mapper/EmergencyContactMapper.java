package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.EmergencyContactRequestDto;
import com.nova.talentnova.dto.EmergencyContactResponseDto;
import com.nova.talentnova.model.EmergencyContact;
import com.nova.talentnova.model.Employee;

public class EmergencyContactMapper {

    // DTO -> ENTIDAD
    public static EmergencyContact toEntity(EmergencyContactRequestDto dto, Employee employee) {
        if (dto == null) return null;

        EmergencyContact contact = new EmergencyContact();
        contact.setEmployee(employee);
        contact.setName(dto.getName());
        contact.setRelationship(dto.getRelationship());
        contact.setMobilePhone(dto.getMobilePhone());
        contact.setAddress(dto.getAddress());

        return contact;
    }

    // ENTIDAD -> DTO
    public static EmergencyContactResponseDto toResponseDto(EmergencyContact entity) {
        if (entity == null) return null;

        EmergencyContactResponseDto dto = new EmergencyContactResponseDto();
        dto.setId(entity.getId());
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
        }
        dto.setName(entity.getName());
        dto.setRelationship(entity.getRelationship());
        dto.setMobilePhone(entity.getMobilePhone());
        dto.setAddress(entity.getAddress());

        return dto;
    }
}