package com.nova.talentnova.controller;

import com.nova.talentnova.dto.ApiResponse;
import com.nova.talentnova.dto.WorkShiftRequestDTO;
import com.nova.talentnova.dto.WorkShiftResponseDTO;
import com.nova.talentnova.service.IWorkShiftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-shift")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class WorkShiftController {

    private final IWorkShiftService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WorkShiftResponseDTO>>> getAll() {
        List<WorkShiftResponseDTO> list = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("Turnos listados con éxito", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkShiftResponseDTO>> getById(@PathVariable Long id) {
        WorkShiftResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Turno encontrado con éxito", dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkShiftResponseDTO>> create(@Valid @RequestBody WorkShiftRequestDTO dto) {
        WorkShiftResponseDTO created = service.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Turno registrado con éxito", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkShiftResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody WorkShiftRequestDTO dto) {
        WorkShiftResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Turno actualizado con éxito", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Turno eliminado con éxito", null));
    }
}