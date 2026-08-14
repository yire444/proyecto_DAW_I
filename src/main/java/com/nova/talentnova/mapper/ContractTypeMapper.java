package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.ContractTypeRequestDTO;
import com.nova.talentnova.dto.ContractTypeResponseDTO;
import com.nova.talentnova.model.ContractType;
import org.springframework.stereotype.Component;

@Component
public class ContractTypeMapper {

    public ContractType toEntity(ContractTypeRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        ContractType entity = new ContractType();
        entity.setName(dto.getName());
        return entity;
    }

    public ContractTypeResponseDTO toResponseDTO(ContractType entity) {
        if (entity == null) {
            return null;
        }
        ContractTypeResponseDTO dto = new ContractTypeResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}