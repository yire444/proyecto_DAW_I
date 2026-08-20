package com.nova.talentnova.service;

import com.nova.talentnova.dto.WorkShiftRequestDTO;
import com.nova.talentnova.dto.WorkShiftResponseDTO;
import com.nova.talentnova.mapper.WorkShiftMapper;
import com.nova.talentnova.model.Company;
import com.nova.talentnova.model.WorkShift;
import com.nova.talentnova.repository.ICompanyRepository;
import com.nova.talentnova.repository.IWorkShiftRepository;
import com.nova.talentnova.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkShiftServiceImpl implements IWorkShiftService {

    private final IWorkShiftRepository repository;
    private final WorkShiftMapper mapper;
    private final ICompanyRepository companyRepository;

    // Inyectamos esto para leer el token directamente
    private final HttpServletRequest request;
    private final JwtUtils jwtUtils;

    // LISTAR
    @Override
    public List<WorkShiftResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @Override
    public WorkShiftResponseDTO findById(Long id) {
        WorkShift entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno de trabajo no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    // REGISTRAR (A prueba de fallos)
    @Override
    public WorkShiftResponseDTO register(WorkShiftRequestDTO dto) {

        // 1. Extraer el token de la petición HTTP
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("No se encontró el token de autorización en la petición.");
        }
        String token = authHeader.substring(7);

        // 2. Extraer el ID exacto de la empresa directamente del token
        Long companyId = jwtUtils.getCompanyIdFromToken(token);

        // 3. Buscar la empresa por su ID (¡Esto no falla por espacios ni mayúsculas!)
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("No se encontró la empresa activa con ID: " + companyId));

        // 4. Validar duplicidad
        if (repository.findByCompanyIdAndName(company.getId(), dto.getName()).isPresent()) {
            throw new RuntimeException("Ya existe un turno activo con el nombre: " + dto.getName());
        }

        // 5. Mapear y guardar
        WorkShift entity = new WorkShift();
        entity.setName(dto.getName());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setCompany(company);

        WorkShift savedEntity = repository.save(entity);
        return mapper.toResponseDTO(savedEntity);
    }

    // ACTUALIZAR
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

    // ELIMINAR
    @Override
    public void delete(Long id) {
        WorkShift entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno de trabajo no encontrado con ID: " + id));
        repository.delete(entity);
    }
}