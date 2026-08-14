package com.nova.talentnova.service;

import com.nova.talentnova.dto.WorkShiftRequestDTO;
import com.nova.talentnova.dto.WorkShiftResponseDTO;
import com.nova.talentnova.mapper.WorkShiftMapper;
import com.nova.talentnova.model.WorkShift;
import com.nova.talentnova.repository.IWorkShiftRepository;
import com.nova.talentnova.service.IWorkShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkShiftServiceImpl implements IWorkShiftService {

    private final IWorkShiftRepository repository;
    private final WorkShiftMapper mapper;

    //LISTAR
    @Override
    public List<WorkShiftResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //BUSCAR POR ID
    @Override
    public WorkShiftResponseDTO findById(Long id) {
        WorkShift entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno de trabajo no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    //REGISTRAR
    @Override
    public WorkShiftResponseDTO register(WorkShiftRequestDTO dto) {
        if (repository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Ya existe un turno de trabajo con el nombre: " + dto.getName());
        }
        WorkShift entity = mapper.toEntity(dto);
        WorkShift saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    //ACTUALIZAR
    @Override
    public WorkShiftResponseDTO update(Long id, WorkShiftRequestDTO dto) {
        WorkShift existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno de trabajo no encontrado con ID: " + id));

        existing.setName(dto.getName());
        existing.setStartTime(dto.getStartTime());
        existing.setEndTime(dto.getEndTime());

        WorkShift updated = repository.save(existing);
        return mapper.toResponseDTO(updated);
    }

    //ELIMINAR
    @Override
    public void delete(Long id) {
        WorkShift entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno de trabajo no encontrado con ID: " + id));
        repository.delete(entity);
    }
}