package com.nova.talentnova.service;

import com.nova.talentnova.dto.PensionSchemeRequestDTO;
import com.nova.talentnova.dto.PensionSchemeResponseDTO;
import com.nova.talentnova.mapper.PensionSchemeMapper;
import com.nova.talentnova.model.PensionScheme;
import com.nova.talentnova.repository.IPensionSchemeRepository;
import com.nova.talentnova.service.IPensionSchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PensionSchemeServiceImpl implements IPensionSchemeService {

    private final IPensionSchemeRepository repository;
    private final PensionSchemeMapper mapper;

    //LISTAR
    @Override
    public List<PensionSchemeResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //BUSCAR POR ID
    @Override
    public PensionSchemeResponseDTO findById(Long id) {
        PensionScheme entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Régimen de pensiones no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    //REGISTRAR
    @Override
    public PensionSchemeResponseDTO register(PensionSchemeRequestDTO dto) {
        if (repository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Ya existe un régimen de pensiones con el nombre: " + dto.getName());
        }
        PensionScheme entity = mapper.toEntity(dto);
        PensionScheme saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    //ACTUALIZAR
    @Override
    public PensionSchemeResponseDTO update(Long id, PensionSchemeRequestDTO dto) {
        PensionScheme existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Régimen de pensiones no encontrado con ID: " + id));

        existing.setName(dto.getName());
        PensionScheme updated = repository.save(existing);
        return mapper.toResponseDTO(updated);
    }

    //ELIMINAR
    @Override
    public void delete(Long id) {
        PensionScheme entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Régimen de pensiones no encontrado con ID: " + id));
        repository.delete(entity);
    }
}