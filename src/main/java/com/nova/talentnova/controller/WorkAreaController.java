package com.nova.talentnova.controller;

import com.nova.talentnova.dto.WorkAreaRequestDto;
import com.nova.talentnova.dto.WorkAreaResponseDto;
import com.nova.talentnova.service.IWorkAreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-area")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class WorkAreaController {

    private final IWorkAreaService service;

    //LISTAR
    @GetMapping
    public ResponseEntity<List<WorkAreaResponseDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkAreaResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    //REGISTRAR
    @PostMapping
    public ResponseEntity<WorkAreaResponseDto> create(@Valid @RequestBody WorkAreaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerWorkArea(dto));
    }

    //ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<WorkAreaResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody WorkAreaRequestDto dto) {
        return ResponseEntity.ok(service.updateWorkArea(id, dto));
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteWorkArea(id);
        return ResponseEntity.noContent().build();
    }
}