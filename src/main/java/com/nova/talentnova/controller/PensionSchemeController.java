package com.nova.talentnova.controller;

import com.nova.talentnova.dto.ApiResponse;
import com.nova.talentnova.dto.PensionSchemeRequestDTO;
import com.nova.talentnova.dto.PensionSchemeResponseDTO;
import com.nova.talentnova.service.IPensionSchemeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pension-schemes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PensionSchemeController {

    private final IPensionSchemeService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PensionSchemeResponseDTO>>> getAll() {
        List<PensionSchemeResponseDTO> list = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("Regímenes de pensiones listados con éxito", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PensionSchemeResponseDTO>> getById(@PathVariable Long id) {
        PensionSchemeResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Régimen de pensiones encontrado con éxito", dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PensionSchemeResponseDTO>> register(@Valid @RequestBody PensionSchemeRequestDTO dto) {
        PensionSchemeResponseDTO created = service.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Régimen de pensiones registrado con éxito", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PensionSchemeResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody PensionSchemeRequestDTO dto) {
        PensionSchemeResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Régimen de pensiones actualizado con éxito", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Régimen de pensiones eliminado con éxito", null));
    }
}