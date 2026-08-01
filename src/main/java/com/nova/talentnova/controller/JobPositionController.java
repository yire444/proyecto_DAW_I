package com.nova.talentnova.controller;

import com.nova.talentnova.dto.ApiResponse;
import com.nova.talentnova.dto.JobPositionRequestDTO;
import com.nova.talentnova.dto.JobPositionResponseDTO;
import com.nova.talentnova.service.IJobPositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-positions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class JobPositionController {

    private final IJobPositionService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<JobPositionResponseDTO>>> getAll() {
        List<JobPositionResponseDTO> list = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("Puestos listados con éxito", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobPositionResponseDTO>> getById(@PathVariable Integer id) {
        JobPositionResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Puesto encontrado con el id:" + id, dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<JobPositionResponseDTO>> create(@Valid @RequestBody JobPositionRequestDTO dto) {
        JobPositionResponseDTO created = service.registerJobPosition(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Puesto registrado con éxito", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<JobPositionResponseDTO>> update(
            @PathVariable Integer id, 
            @Valid @RequestBody JobPositionRequestDTO dto) {
        JobPositionResponseDTO updated = service.updateJobPosition(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Puesto actualizado con éxito", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        service.deleteJobPosition(id);
        return ResponseEntity.ok(ApiResponse.success("Puesto eliminado con éxito", null));
    }
}