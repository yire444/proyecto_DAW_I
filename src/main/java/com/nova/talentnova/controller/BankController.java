package com.nova.talentnova.controller;

import com.nova.talentnova.dto.ApiResponse;
import com.nova.talentnova.dto.BankRequestDTO;
import com.nova.talentnova.dto.BankResponseDTO;
import com.nova.talentnova.service.IBankService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BankController {

    private final IBankService service;

    //LISTAR
    @GetMapping
    public ResponseEntity<ApiResponse<List<BankResponseDTO>>> getAll() {
        List<BankResponseDTO> list = service.findAll();
        return ResponseEntity.ok(ApiResponse.success("Bancos listados con éxito", list));
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BankResponseDTO>> getById(@PathVariable Long id) {
        BankResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Banco encontrado con éxito", dto));
    }

    //REGISTRAR
    @PostMapping
    public ResponseEntity<ApiResponse<BankResponseDTO>> register(@Valid @RequestBody BankRequestDTO dto) {
        BankResponseDTO created = service.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Banco registrado con éxito", created));
    }

    //ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BankResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody BankRequestDTO dto) {
        BankResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Banco actualizado con éxito", updated));
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Banco eliminado con éxito", null));
    }
}