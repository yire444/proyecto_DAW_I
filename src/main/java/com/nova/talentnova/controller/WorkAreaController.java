package com.nova.talentnova.controller;

import com.nova.talentnova.dto.WorkAreaRequestDto;
import com.nova.talentnova.dto.WorkAreaResponseDto;
import com.nova.talentnova.security.JwtUtils; // <-- Importamos tu JwtUtils
import com.nova.talentnova.service.IWorkAreaService;
import jakarta.servlet.http.HttpServletRequest; // <-- Importamos para leer la cabecera
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/work-area")
@RequiredArgsConstructor
public class WorkAreaController {

    private final IWorkAreaService workAreaService;
    private final JwtUtils jwtUtils;
    // 1. LISTAR
    @GetMapping
    public ResponseEntity<List<WorkAreaResponseDto>> getAllByCompany(HttpServletRequest request) {
        Long companyId = extraerCompanyId(request);
        return ResponseEntity.ok(workAreaService.getAllWorkAreasByCompany(companyId));
    }

    // 2. REGISTRAR
    @PostMapping
    public ResponseEntity<WorkAreaResponseDto> create(
            @Valid @RequestBody WorkAreaRequestDto requestDto,
            HttpServletRequest request) {

        Long companyId = extraerCompanyId(request);
        WorkAreaResponseDto nuevaArea = workAreaService.createWorkArea(requestDto, companyId);
        return new ResponseEntity<>(nuevaArea, HttpStatus.CREATED);
    }

    // 3. ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<WorkAreaResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody WorkAreaRequestDto requestDto,
            HttpServletRequest request) {

        Long companyId = extraerCompanyId(request);
        return ResponseEntity.ok(workAreaService.updateWorkArea(id, requestDto, companyId));
    }

    // 4. ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            HttpServletRequest request) {

        Long companyId = extraerCompanyId(request);
        workAreaService.deleteWorkArea(id, companyId);
        return ResponseEntity.noContent().build();
    }

    //OBTENER EL TOKEN DESDE EL JWT
    private Long extraerCompanyId(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return jwtUtils.getCompanyIdFromToken(token);
        }

        throw new RuntimeException("No se encontró un token válido en la petición");
    }
}