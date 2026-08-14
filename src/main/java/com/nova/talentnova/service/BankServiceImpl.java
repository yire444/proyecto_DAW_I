package com.nova.talentnova.service;

import com.nova.talentnova.dto.BankRequestDTO;
import com.nova.talentnova.dto.BankResponseDTO;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements IBankService {

    private final IBankRepository repository;
    private final BankMapper mapper;

    @Override
    public List<BankResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BankResponseDTO findById(Long id) {
        Bank entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banco no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    @Override
    public BankResponseDTO register(BankRequestDTO dto) {
        if (repository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Ya existe un banco registrado con el nombre: " + dto.getName());
        }
        Bank entity = mapper.toEntity(dto);
        Bank saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    @Override
    public BankResponseDTO update(Long id, BankRequestDTO dto) {
        Bank existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banco no encontrado con ID: " + id));

        existing.setName(dto.getName());
        Bank updated = repository.save(existing);
        return mapper.toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Bank entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banco no encontrado con ID: " + id));
        repository.delete(entity);
    }
}
