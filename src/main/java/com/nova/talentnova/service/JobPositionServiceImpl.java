package com.nova.talentnova.service;
import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.dto.JobPositionRequestDTO;
import com.nova.talentnova.dto.JobPositionResponseDTO;
import com.nova.talentnova.mapper.JobPositionMapper;
import com.nova.talentnova.model.JobPosition;
import com.nova.talentnova.model.WorkArea;
import com.nova.talentnova.repository.IJobPositionRepository;
import com.nova.talentnova.repository.IWorkAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements IJobPositionService {

    private final IJobPositionRepository repository;
    private final IWorkAreaRepository workAreaRepository;
    private final JobPositionMapper mapper;

    // LISTAR
    @Override
    public List<JobPositionResponseDTO> findAll() {
        return repository.findByStatus(GeneralStatus.ACTIVE).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @Override
    public JobPositionResponseDTO findById(Long id) {
        JobPosition entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    // REGISTRAR
    @Override
    public JobPositionResponseDTO registerJobPosition(JobPositionRequestDTO dto) {
        WorkArea workArea = workAreaRepository.findById(dto.getWorkAreaId())
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + dto.getWorkAreaId()));

        if (repository.findByNameAndStatus(dto.getName(), GeneralStatus.ACTIVE).isPresent()) {
            throw new RuntimeException("Ya existe un puesto de trabajo activo con el nombre: " + dto.getName());
        }

        JobPosition entity = mapper.toEntity(dto, workArea);
        JobPosition savedEntity = repository.save(entity);
        return mapper.toResponseDTO(savedEntity);
    }

    // ACTUALIZAR
    @Override
    public JobPositionResponseDTO updateJobPosition(Long id, JobPositionRequestDTO dto) {
        JobPosition existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado con ID: " + id));

        // Buscar la nueva área de trabajo si cambió
        WorkArea workArea = workAreaRepository.findById(dto.getWorkAreaId())
                .orElseThrow(() -> new RuntimeException("Área de trabajo no encontrada con ID: " + dto.getWorkAreaId()));

        existingEntity.setName(dto.getName());
        existingEntity.setDescription(dto.getDescription());
        existingEntity.setWorkArea(workArea);

        JobPosition updatedEntity = repository.save(existingEntity);
        return mapper.toResponseDTO(updatedEntity);
    }

    // ELIMINAR
    @Override
    public void deleteJobPosition(Long id) {
        JobPosition entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado con ID: " + id));

        entity.setStatus(GeneralStatus.INACTIVE);
        repository.save(entity);
    }
}