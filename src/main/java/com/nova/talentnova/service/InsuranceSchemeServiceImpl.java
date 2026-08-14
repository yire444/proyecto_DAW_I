package com.nova.talentnova.service;

import com.nova.talentnova.dto.InsuranceSchemeRequestDTO;
import com.nova.talentnova.dto.InsuranceSchemeResponseDTO;
import com.nova.talentnova.mapper.InsuranceSchemeMapper;
import com.nova.talentnova.model.InsuranceScheme;
import com.nova.talentnova.repository.IInsuranceSchemeRepository;
import com.nova.talentnova.service.IInsuranceSchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsuranceSchemeServiceImpl implements IInsuranceSchemeService {

    private final IInsuranceSchemeRepository repository;
    private final InsuranceSchemeMapper mapper;

    //LISTAR
    @Override
    public List<InsuranceSchemeResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //BUSCAR PPR ID
    @Override
    public InsuranceSchemeResponseDTO findById(Long id) {
        InsuranceScheme entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Régimen de seguro no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    //REGISTRAR
    @Override
    public InsuranceSchemeResponseDTO register(InsuranceSchemeRequestDTO dto) {
        if (repository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Ya existe un régimen de seguro con el nombre: " + dto.getName());
        }
        InsuranceScheme entity = mapper.toEntity(dto);
        InsuranceScheme saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    //ACTUALIZAR
    @Override
    public InsuranceSchemeResponseDTO update(Long id, InsuranceSchemeRequestDTO dto) {
        InsuranceScheme existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Régimen de seguro no encontrado con ID: " + id));

        existing.setName(dto.getName());
        InsuranceScheme updated = repository.save(existing);
        return mapper.toResponseDTO(updated);
    }

    //ELIMINAR
    @Override
    public void delete(Long id) {
        InsuranceScheme entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Régimen de seguro no encontrado con ID: " + id));
        repository.delete(entity);
    }
}