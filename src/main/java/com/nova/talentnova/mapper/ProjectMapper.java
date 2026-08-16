package com.nova.talentnova.mapper;

import com.nova.talentnova.ProjectStatus;
import com.nova.talentnova.dto.ProjectRequestDto;
import com.nova.talentnova.dto.ProjectResponseDto;
import com.nova.talentnova.model.Company;
import com.nova.talentnova.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    // ENTIDAD -> DTO
    public ProjectResponseDto toResponseDto(Project project) {
        if (project == null) {
            return null;
        }

        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());

        if (project.getCompany() != null) {
            dto.setCompanyId(project.getCompany().getId());
            dto.setCompanyName(project.getCompany().getNameCompany());
        }

        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setArea(project.getArea());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setStatus(project.getStatus());

        return dto;
    }

    // DTO -> ENTIDAD
    public Project toEntity(ProjectRequestDto requestDto, Company company) {
        if (requestDto == null || company == null) {
            return null;
        }

        Project project = new Project();
        project.setCompany(company);
        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());
        project.setArea(requestDto.getArea());
        project.setStartDate(requestDto.getStartDate());
        project.setEndDate(requestDto.getEndDate());

        project.setStatus(ProjectStatus.ACTIVE);

        return project;
    }
}