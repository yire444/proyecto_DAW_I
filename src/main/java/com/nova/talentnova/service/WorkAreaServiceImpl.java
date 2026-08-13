package com.nova.talentnova.service;

import com.nova.talentnova.dto.WorkAreaRequestDto;
import com.nova.talentnova.dto.WorkAreaResponseDto;
import com.nova.talentnova.mapper.WorkAreaMapper;
import com.nova.talentnova.model.WorkArea;
import com.nova.talentnova.repository.IWorkAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkAreaServiceImpl implements IWorkAreaService {

    private final IWorkAreaRepository repository;
    private final WorkAreaMapper mapper;

    // LISTAR LAS ÁREAS DE TRABAJO ACTIVAS
    @Override
    public List<WorkAreaResponseDto> findAll() {
        return repository.findByStatusTrue().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @Override
    public WorkAreaResponseDto findById(Integer id) {
        WorkArea entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + id));
        return mapper.toResponseDto(entity);
    }

    // REGISTRAR NUEVO DEPARTAMENTO
    @Override
    public WorkAreaResponseDto registerWorkArea(WorkAreaRequestDto dto) {
        WorkArea entity = mapper.toEntity(dto);
        WorkArea savedEntity = repository.save(entity);
        return mapper.toResponseDto(savedEntity);
    }

    // ACTUALIZAR POR ID
    @Override
    public WorkAreaResponseDto updateWorkArea(Integer id, WorkAreaRequestDto dto) {
        WorkArea existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + id));

        existingEntity.setName(dto.getName());
        existingEntity.setDescription(dto.getDescription());

        WorkArea updatedEntity = repository.save(existingEntity);
        return mapper.toResponseDto(updatedEntity);
    }

    // ELIMINAR
    @Override
    public void deleteWorkArea(Integer id) {
        WorkArea entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + id));

        entity.setStatus(false);
        repository.save(entity);
    }
}