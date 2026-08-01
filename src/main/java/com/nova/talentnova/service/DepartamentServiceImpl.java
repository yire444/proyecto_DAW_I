package com.nova.talentnova.service;

import com.nova.talentnova.dto.DepartamentRequestDTO;
import com.nova.talentnova.dto.DepartamentResponseDTO;
import com.nova.talentnova.mapper.DepartamentMapper;
import com.nova.talentnova.model.Departament;
import com.nova.talentnova.repository.IDepartamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartamentServiceImpl implements IDepartamentService {

    private final IDepartamentRepository repository;
    private final DepartamentMapper mapper;

    // LISTAR LOS DEPARTAMENTOS
    @Override
    public List<DepartamentResponseDTO> findAll() {
        return repository.findByStatusTrue().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @Override
    public DepartamentResponseDTO findById(Integer id) {
        Departament entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    // REGISTRAR NUEVO DEPARTAMENTO
    @Override
    public DepartamentResponseDTO registerDepartament(DepartamentRequestDTO dto) {
        Departament entity = mapper.toEntity(dto);
        Departament savedEntity = repository.save(entity);
        return mapper.toResponseDTO(savedEntity);
    }

    // ACTUALIZAR POR ID
    @Override
    public DepartamentResponseDTO updateDepartament(Integer id, DepartamentRequestDTO dto) {
        Departament existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + id));

        existingEntity.setName(dto.getName());
        existingEntity.setDescription(dto.getDescription());

        Departament updatedEntity = repository.save(existingEntity);
        return mapper.toResponseDTO(updatedEntity);
    }

    // ELIMINAR
    @Override
    public void deleteDepartament(Integer id) {
        Departament entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + id));

        entity.setStatus(false);
        repository.save(entity);
    }
}