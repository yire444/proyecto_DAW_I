package com.nova.talentnova.mapper;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.dto.JobPositionRequestDTO;
import com.nova.talentnova.dto.JobPositionResponseDTO;
import com.nova.talentnova.model.JobPosition;
import com.nova.talentnova.model.WorkArea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobPositionMapper {

    private final WorkAreaMapper workAreaMapper;

    // Convierte DTO -> ENTIDAD
    public JobPosition toEntity(JobPositionRequestDTO dto, WorkArea workArea) {
        if (dto == null) {
            return null;
        }
        JobPosition jobPosition = new JobPosition();
        jobPosition.setName(dto.getName());
        jobPosition.setDescription(dto.getDescription());
        jobPosition.setStatus(GeneralStatus.ACTIVE);
        jobPosition.setWorkArea(workArea);
        return jobPosition;
    }

    // Convierte ENTIDAD -> DTO
    public JobPositionResponseDTO toResponseDTO(JobPosition entity) {
        if (entity == null) {
            return null;
        }
        JobPositionResponseDTO dto = new JobPositionResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());

        //ASIGNAR EL PUESTO DE TRAAJO A UN ÁREA
        if (entity.getWorkArea() != null) {
            dto.setWorkArea(workAreaMapper.toResponseDto(entity.getWorkArea()));
        }

        return dto;
    }
}