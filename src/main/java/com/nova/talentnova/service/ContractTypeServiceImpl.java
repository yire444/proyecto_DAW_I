package com.nova.talentnova.service;

import com.nova.talentnova.dto.ContractTypeRequestDTO;
import com.nova.talentnova.dto.ContractTypeResponseDTO;
import com.nova.talentnova.mapper.ContractTypeMapper;
import com.nova.talentnova.model.ContractType;
import com.nova.talentnova.repository.IContractTypeRepository;
import com.nova.talentnova.service.IContractTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractTypeServiceImpl implements IContractTypeService {

    private final IContractTypeRepository repository;
    private final ContractTypeMapper mapper;

    //LISTAR
    @Override
    public List<ContractTypeResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //BUSCAR POR ID
    @Override
    public ContractTypeResponseDTO findById(Long id) {
        ContractType entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de contrato no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    //REGISTRAR
    @Override
    public ContractTypeResponseDTO register(ContractTypeRequestDTO dto) {
        if (repository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Ya existe un tipo de contrato con el nombre: " + dto.getName());
        }
        ContractType entity = mapper.toEntity(dto);
        ContractType saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    //ACTUALIZAR
    @Override
    public ContractTypeResponseDTO update(Long id, ContractTypeRequestDTO dto) {
        ContractType existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de contrato no encontrado con ID: " + id));

        existing.setName(dto.getName());
        ContractType updated = repository.save(existing);
        return mapper.toResponseDTO(updated);
    }

    //ELIMINAR
    @Override
    public void delete(Long id) {
        ContractType entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de contrato no encontrado con ID: " + id));
        repository.delete(entity);
    }
}