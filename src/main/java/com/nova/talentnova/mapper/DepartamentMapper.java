package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.DepartamentRequestDTO;
import com.nova.talentnova.dto.DepartamentResponseDTO;
import com.nova.talentnova.model.Departament;
import org.springframework.stereotype.Component;

@Component
public class DepartamentMapper {

    public Departament toEntity(DepartamentRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Departament departament = new Departament();
        departament.setName(dto.getName());
        departament.setDescription(dto.getDescription());
        return departament;
    }

    public DepartamentResponseDTO toResponseDTO(Departament entity) {
        if (entity == null) {
            return null;
        }
        DepartamentResponseDTO dto = new DepartamentResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}