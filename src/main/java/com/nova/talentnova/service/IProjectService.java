package com.nova.talentnova.service;

import com.nova.talentnova.dto.ProjectRequestDto;
import com.nova.talentnova.dto.ProjectResponseDto;

import java.util.List;

public interface IProjectService {

    // REGISTRAR (Aquí es el ID de la Empresa)
    ProjectResponseDto createProject(Long companyId, ProjectRequestDto dto);

    // LISTAR PROYECTOS DE UNA EMPRESA (Aquí es el ID de la Empresa)
    List<ProjectResponseDto> getProjectsByCompany(Long companyId);

    // BUSCAR POR ID (Aquí SÍ es el ID del Proyecto)
    ProjectResponseDto getProjectById(Long id);

    // ACTUALIZAR PROYECTO (Aquí SÍ es el ID del Proyecto)
    ProjectResponseDto updateProject(Long id, ProjectRequestDto dto);

    // ELIMINAR PROYECTO (Aquí SÍ es el ID del Proyecto)
    void deleteProject(Long id);
}