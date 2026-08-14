package com.nova.talentnova.controller;

import com.nova.talentnova.dto.ApiResponse;
import com.nova.talentnova.dto.ContractTypeRequestDTO;
import com.nova.talentnova.dto.ContractTypeResponseDTO;
import com.nova.talentnova.service.IContractTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract-type")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContractTypeController {

    private final IContractTypeService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContractTypeResponseDTO>>> getAll() {
        List<ContractTypeResponseDTO> list = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("Tipos de contrato listados con éxito", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContractTypeResponseDTO>> getById(@PathVariable Long id) {
        ContractTypeResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Tipo de contrato encontrado con éxito", dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContractTypeResponseDTO>> create(@Valid @RequestBody ContractTypeRequestDTO dto) {
        ContractTypeResponseDTO created = service.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tipo de contrato registrado con éxito", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContractTypeResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody ContractTypeRequestDTO dto) {
        ContractTypeResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Tipo de contrato actualizado con éxito", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Tipo de contrato eliminado con éxito", null));
    }
}