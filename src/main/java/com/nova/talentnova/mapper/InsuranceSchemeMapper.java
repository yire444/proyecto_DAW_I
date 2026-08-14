package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.InsuranceSchemeRequestDTO;
import com.nova.talentnova.dto.InsuranceSchemeResponseDTO;
import com.nova.talentnova.model.InsuranceScheme;
import org.springframework.stereotype.Component;

@Component
public class InsuranceSchemeMapper {

    public InsuranceScheme toEntity(InsuranceSchemeRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        InsuranceScheme entity = new InsuranceScheme();
        entity.setName(dto.getName());
        return entity;
    }

    public InsuranceSchemeResponseDTO toResponseDTO(InsuranceScheme entity) {
        if (entity == null) {
            return null;
        }
        InsuranceSchemeResponseDTO dto = new InsuranceSchemeResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}