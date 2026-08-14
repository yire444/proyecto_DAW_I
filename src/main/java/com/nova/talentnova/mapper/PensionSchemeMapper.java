package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.PensionSchemeRequestDTO;
import com.nova.talentnova.dto.PensionSchemeResponseDTO;
import com.nova.talentnova.model.PensionScheme;
import org.springframework.stereotype.Component;

@Component
public class PensionSchemeMapper {

    public PensionScheme toEntity(PensionSchemeRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        PensionScheme entity = new PensionScheme();
        entity.setName(dto.getName());
        return entity;
    }

    public PensionSchemeResponseDTO toResponseDTO(PensionScheme entity) {
        if (entity == null) {
            return null;
        }
        PensionSchemeResponseDTO dto = new PensionSchemeResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}