package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.JobPositionRequestDTO;
import com.nova.talentnova.dto.JobPositionResponseDTO;
import com.nova.talentnova.model.JobPosition;
import org.springframework.stereotype.Component;

@Component
public class JobPositionMapper {

    public JobPosition toEntity(JobPositionRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        JobPosition jobPosition = new JobPosition();
        jobPosition.setName(dto.getName());
        jobPosition.setDescription(dto.getDescription());
        return jobPosition;
    }

    public JobPositionResponseDTO toResponseDTO(JobPosition entity) {
        if (entity == null) {
            return null;
        }
        JobPositionResponseDTO dto = new JobPositionResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}