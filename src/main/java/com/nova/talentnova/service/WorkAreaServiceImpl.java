package com.nova.talentnova.service.impl; // O tu paquete correspondiente

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

    // LISTAR
    @Override
    public List<WorkAreaResponseDto> findAll() {
        return repository.findByStatus(GeneralStatus.ACTIVE).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @Override
    public WorkAreaResponseDto findById(Long id) {
        WorkArea entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + id));
        return mapper.toResponseDto(entity);
    }

    // REGISTRAR
    @Override
    public WorkAreaResponseDto registerWorkArea(WorkAreaRequestDto dto) {
        // Validar si ya existe una con el mismo nombre para evitar duplicados
        if (repository.findByNameAndStatus(dto.getName(), GeneralStatus.ACTIVE).isPresent()) {
            throw new RuntimeException("Ya existe un área de trabajo activa con el nombre: " + dto.getName());
        }

        WorkArea entity = mapper.toEntity(dto);
        WorkArea savedEntity = repository.save(entity);
        return mapper.toResponseDto(savedEntity);
    }

    // ACTUALIZAR POR ID
    @Override
    public WorkAreaResponseDto updateWorkArea(Long id, WorkAreaRequestDto dto) {
        WorkArea existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + id));

        existingEntity.setName(dto.getName());
        existingEntity.setDescription(dto.getDescription());

        WorkArea updatedEntity = repository.save(existingEntity);
        return mapper.toResponseDto(updatedEntity);
    }

    // ELIMINAR
    @Override
    public void deleteWorkArea(Long id) {
        WorkArea entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + id));

        entity.setStatus(GeneralStatus.INACTIVE);
        repository.save(entity);
    }
}