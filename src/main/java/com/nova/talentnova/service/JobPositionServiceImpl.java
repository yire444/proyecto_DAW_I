package com.nova.talentnova.service;

import com.nova.talentnova.dto.JobPositionRequestDTO;
import com.nova.talentnova.dto.JobPositionResponseDTO;
import com.nova.talentnova.mapper.JobPositionMapper;
import com.nova.talentnova.model.JobPosition;
import com.nova.talentnova.repository.IJobPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements IJobPositionService {

    private final IJobPositionRepository repository;
    private final JobPositionMapper mapper;

    // LISTAR LOS PUESTOS DE TRABAJO
    @Override
    public List<JobPositionResponseDTO> findAll() {
        return repository.findByStatusTrue().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR UN TRABAJO POR ID
    @Override
    public JobPositionResponseDTO findById(Integer id) {
        JobPosition entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    // REGISTRAR UN PUESTO DE TRABAJO
    @Override
    public JobPositionResponseDTO registerJobPosition(JobPositionRequestDTO dto) {
        JobPosition entity = mapper.toEntity(dto);
        JobPosition savedEntity = repository.save(entity);
        return mapper.toResponseDTO(savedEntity);
    }

    // ACTUALIZAR PUESTO DE TRABAJO
    @Override
    public JobPositionResponseDTO updateJobPosition(Integer id, JobPositionRequestDTO dto) {
        JobPosition existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado con ID: " + id));

        existingEntity.setName(dto.getName());
        existingEntity.setDescription(dto.getDescription());

        JobPosition updatedEntity = repository.save(existingEntity);
        return mapper.toResponseDTO(updatedEntity);
    }

    // ELIMINAR PUESTO DE TRABAJO
    @Override
    public void deleteJobPosition(Integer id) {
        JobPosition entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto de trabajo no encontrado con ID: " + id));

        entity.setStatus(false);
        repository.save(entity);
    }
}