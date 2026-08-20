package com.nova.talentnova.mapper;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.dto.WorkAreaRequestDto;
import com.nova.talentnova.dto.WorkAreaResponseDto;
import com.nova.talentnova.model.WorkArea;
import org.springframework.stereotype.Component;

@Component
public class WorkAreaMapper {

    // Convierte DTO -> ENTIDAD
    public WorkArea toEntity(WorkAreaRequestDto dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        WorkArea workArea = new WorkArea();
        workArea.setCompanyId(companyId);
        workArea.setName(dto.getName());
        workArea.setDescription(dto.getDescription());
        workArea.setStatus(GeneralStatus.ACTIVE);
        return workArea;
    }

    // Convierte ENTIDAD -> DTO
    public WorkAreaResponseDto toResponseDto(WorkArea entity) {
        if (entity == null) {
            return null;
        }
        WorkAreaResponseDto dto = new WorkAreaResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}