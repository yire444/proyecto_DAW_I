package com.nova.talentnova.controller;

import com.nova.talentnova.dto.ProjectRequestDto;
import com.nova.talentnova.dto.ProjectResponseDto;
import com.nova.talentnova.service.IProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProjectController {

    private final IProjectService projectService;

    //REGISTRAR
    @PostMapping("/company/{companyId}")
    public ResponseEntity<ProjectResponseDto> createProject(
            @PathVariable Long companyId,
            @Valid @RequestBody ProjectRequestDto dto) {
        ProjectResponseDto response = projectService.createProject(companyId, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //LISTAR POR EMPRESA
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ProjectResponseDto>> getProjectsByCompany(@PathVariable Long companyId) {
        List<ProjectResponseDto> responses = projectService.getProjectsByCompany(companyId);
        return ResponseEntity.ok(responses);
    }

    //BUSCA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        ProjectResponseDto response = projectService.getProjectById(id);
        return ResponseEntity.ok(response);
    }

    //ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequestDto dto) {
        ProjectResponseDto response = projectService.updateProject(id, dto);
        return ResponseEntity.ok(response);
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}