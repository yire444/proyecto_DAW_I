package com.nova.talentnova.service.impl;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.dto.WorkAreaRequestDto;
import com.nova.talentnova.dto.WorkAreaResponseDto;
import com.nova.talentnova.mapper.WorkAreaMapper;
import com.nova.talentnova.model.WorkArea;
import com.nova.talentnova.repository.IWorkAreaRepository;
import com.nova.talentnova.service.IWorkAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkAreaServiceImpl implements IWorkAreaService {

    private final IWorkAreaRepository repository;
    private final WorkAreaMapper mapper;

    // 1. LISTAR POR EMPRESA
    @Override
    public List<WorkAreaResponseDto> getAllWorkAreasByCompany(Long companyId) {
        //OBTENER ÁREA DE UNA EMPRESA Y LISTAR LAS ACTIVAS
        return repository.findByCompanyId(companyId).stream()
                .filter(workArea -> workArea.getStatus() == GeneralStatus.ACTIVE)
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    //REGISTRAR ÁREA POR EMPRESA
    @Override
    public WorkAreaResponseDto createWorkArea(WorkAreaRequestDto requestDto, Long companyId) {
        //VALIDAR SI YA EXISTE EL NOMBRE DEL ÁREA POR EMPRESA
        boolean nameExists = repository.findByCompanyId(companyId).stream()
                .anyMatch(workArea -> workArea.getName().equalsIgnoreCase(requestDto.getName())
                        && workArea.getStatus() == GeneralStatus.ACTIVE);

        if (nameExists) {
            throw new RuntimeException("Ya existe un área de trabajo activa con el nombre: " + requestDto.getName() + " en su empresa.");
        }

        //GUARDAR
        WorkArea entity = mapper.toEntity(requestDto, companyId);
        WorkArea savedEntity = repository.save(entity);
        return mapper.toResponseDto(savedEntity);
    }

    //ACTUALIZAR ÁREA POR EMPRESA
    @Override
    public WorkAreaResponseDto updateWorkArea(Long id, WorkAreaRequestDto requestDto, Long companyId) {
        WorkArea existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + id));

        //VALIDAR QUE EL ÁREA PERTENECE A ESA EMPRESA
        if (!existingEntity.getCompanyId().equals(companyId)) {
            throw new RuntimeException("Acceso denegado: No tiene permisos para modificar esta área.");
        }

        //DATOS QUE PUEDE ACTUALIZAR
        existingEntity.setName(requestDto.getName());
        existingEntity.setDescription(requestDto.getDescription());

        WorkArea updatedEntity = repository.save(existingEntity);
        return mapper.toResponseDto(updatedEntity);
    }

    //ELIMINAR POR EMPRESA
    @Override
    public void deleteWorkArea(Long id, Long companyId) {
        WorkArea entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + id));

        // Validación de Seguridad Multi-tenant
        if (!entity.getCompanyId().equals(companyId)) {
            throw new RuntimeException("Acceso denegado: No tiene permisos para eliminar esta área.");
        }

        entity.setStatus(GeneralStatus.INACTIVE);
        repository.save(entity);
    }
}