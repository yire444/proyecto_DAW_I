package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.EmergencyContactRequestDto;
import com.nova.talentnova.dto.EmergencyContactResponseDto;
import com.nova.talentnova.model.EmergencyContact;

public class EmergencyContactMapper {

    public static EmergencyContact toEntity(EmergencyContactRequestDto dto) {
        if (dto == null) {return null;}

        EmergencyContact contact = new EmergencyContact();
        contact.setName(dto.getName());
        contact.setRelationShip(dto.getRelationShip());
        contact.setMobilePhone(dto.getMobilePhone());
        contact.setAddress(dto.getAddress());

        return contact;
    }

    public static EmergencyContactResponseDto toResponseDto(EmergencyContact entity) {
        if (entity == null) {return null;}

        EmergencyContactResponseDto dto = new EmergencyContactResponseDto();
        dto.setId(entity.getId());
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
        }
        dto.setName(entity.getName());
        dto.setRelationShip(entity.getRelationShip());
        dto.setMobilePhone(entity.getMobilePhone());
        dto.setAddress(entity.getAddress());

        return dto;
    }
}