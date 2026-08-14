package com.nova.talentnova.controller;

import com.nova.talentnova.dto.ApiResponse;
import com.nova.talentnova.dto.InsuranceSchemeRequestDTO;
import com.nova.talentnova.dto.InsuranceSchemeResponseDTO;
import com.nova.talentnova.service.IInsuranceSchemeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance-scheme")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InsuranceSchemeController {

    private final IInsuranceSchemeService service;

    //LISTAR
    @GetMapping
    public ResponseEntity<ApiResponse<List<InsuranceSchemeResponseDTO>>> getAll() {
        List<InsuranceSchemeResponseDTO> list = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("Regímenes de seguro listados con éxito", list));
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InsuranceSchemeResponseDTO>> getById(@PathVariable Long id) {
        InsuranceSchemeResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Régimen de seguro encontrado con éxito", dto));
    }

    //REGISTRAR
    @PostMapping
    public ResponseEntity<ApiResponse<InsuranceSchemeResponseDTO>> register(@Valid @RequestBody InsuranceSchemeRequestDTO dto) {
        InsuranceSchemeResponseDTO created = service.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Régimen de seguro registrado con éxito", created));
    }

    //ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InsuranceSchemeResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody InsuranceSchemeRequestDTO dto) {
        InsuranceSchemeResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Régimen de seguro actualizado con éxito", updated));
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Régimen de seguro eliminado con éxito", null));
    }
}