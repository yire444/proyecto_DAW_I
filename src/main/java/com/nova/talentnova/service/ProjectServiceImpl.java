package com.nova.talentnova.service;

import com.nova.talentnova.ProjectStatus;
import com.nova.talentnova.dto.ProjectRequestDto;
import com.nova.talentnova.dto.ProjectResponseDto;
import com.nova.talentnova.mapper.ProjectMapper;
import com.nova.talentnova.model.Company;
import com.nova.talentnova.model.Project;
import com.nova.talentnova.repository.ICompanyRepository;
import com.nova.talentnova.repository.IProjectRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    private final IProjectRepository projectRepo;
    private final ICompanyRepository companyRepos;
    private final ProjectMapper projectMap;

    // REGISTRAR
    @Override
    public ProjectResponseDto createProject(Long companyId, ProjectRequestDto dto) {
        //SI YA EXISTE OTRO PROYECTO CON ESE NOMBRE
        if (projectRepo.existsByNameAndCompanyId(dto.getName(), companyId)) {
            throw new IllegalArgumentException("Ya existe un proyecto con el nombre '" + dto.getName() + "' en esta empresa.");
        }

        Company company = companyRepos.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + companyId));

        Project project = projectMap.toEntity(dto, company);
        Project savedProject = projectRepo.save(project);
        return projectMap.toResponseDto(savedProject);
    }

    //LISTAR PROYECTO SEGÚN EMPRESA
    @Override
    public List<ProjectResponseDto> getProjectsByCompany(Long companyId) {
        return projectRepo.findByCompanyId(companyId).stream()
                .map(projectMap::toResponseDto)
                .collect(Collectors.toList());
    }

    //BUSCAR POR ID
    @Override
    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + id));
        return projectMap.toResponseDto(project);
    }

    //ACTUALIZAR
    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto dto) {
        Project existingProject = projectRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado para actualizar con ID: " + id));

        //VALIDAR QUE EL NOMBRE DEL PROYECTO NO EXISTA
        if (!existingProject.getName().equalsIgnoreCase(dto.getName())) {
            if (projectRepo.existsByNameAndCompanyId(dto.getName(), existingProject.getCompany().getId())) {
                throw new IllegalArgumentException("Ya existe otro proyecto con el nombre '" + dto.getName() + "'.");
            }
        }

        //CAMPOS A ACTUALIZAR
        existingProject.setName(dto.getName());
        existingProject.setDescription(dto.getDescription());
        existingProject.setArea(dto.getArea());
        existingProject.setStartDate(dto.getStartDate());
        existingProject.setEndDate(dto.getEndDate());

        //GUARDAR
        Project updatedProject = projectRepo.save(existingProject);
        return projectMap.toResponseDto(updatedProject);
    }

    //ELIMINAR
    @Override
    public void deleteProject(Long id) {

        //BUSCAR PROYECTO
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + id));

        project.setStatus(ProjectStatus.CANCELLED);
        projectRepo.save(project);
    }
}