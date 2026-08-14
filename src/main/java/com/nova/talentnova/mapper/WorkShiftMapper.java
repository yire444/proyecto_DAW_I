package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.WorkShiftRequestDTO;
import com.nova.talentnova.dto.WorkShiftResponseDTO;
import com.nova.talentnova.model.WorkShift;
import org.springframework.stereotype.Component;

@Component
public class WorkShiftMapper {

    public WorkShift toEntity(WorkShiftRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        WorkShift entity = new WorkShift();
        entity.setName(dto.getName());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        return entity;
    }

    public WorkShiftResponseDTO toResponseDTO(WorkShift entity) {
        if (entity == null) {
            return null;
        }
        WorkShiftResponseDTO dto = new WorkShiftResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        return dto;
    }
}