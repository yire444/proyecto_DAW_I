package com.nova.talentnova.controller;

import com.nova.talentnova.dto.ApiResponse;
import com.nova.talentnova.dto.DepartamentRequestDTO;
import com.nova.talentnova.dto.DepartamentResponseDTO;
import com.nova.talentnova.service.IDepartamentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DepartamentController {

    private final IDepartamentService service;

    //LISTAR TODOS
    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartamentResponseDTO>>> getAll() {
        List<DepartamentResponseDTO> list = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("Departamentos listados con éxito", list));
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartamentResponseDTO>> getById(@PathVariable Integer id) {
        DepartamentResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Departamento encontrado con el id:" + id, dto));
    }

    //REGISTRAR
    @PostMapping
    public ResponseEntity<ApiResponse<DepartamentResponseDTO>> create(@Valid @RequestBody DepartamentRequestDTO dto) {
        DepartamentResponseDTO created = service.registerDepartament(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Departamento registrado con éxito", created));
    }

    //ACTUALIZAR POR ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartamentResponseDTO>> update(
            @PathVariable Integer id, 
            @Valid @RequestBody DepartamentRequestDTO dto) {
        DepartamentResponseDTO updated = service.updateDepartament(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Departamento actualizado con éxito", updated));
    }

    //ELIMINAR POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        service.deleteDepartament(id);
        return ResponseEntity.ok(ApiResponse.success("Departamento eliminado con éxito", null));
    }
}