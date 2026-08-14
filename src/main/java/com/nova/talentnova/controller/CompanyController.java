package com.nova.talentnova.controller;

import com.nova.talentnova.dto.CompanyFilterDto;
import com.nova.talentnova.dto.CompanyRequestDto;
import com.nova.talentnova.dto.CompanyResponseDto;
import com.nova.talentnova.dto.CompanyUpdateDto;
import com.nova.talentnova.security.JwtUtils;
import com.nova.talentnova.service.ICompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companie")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CompanyController {

    private final ICompanyService companyService;
    private final JwtUtils jwtUtils;

    //REGISTRAR
    @PostMapping("/register")
    public ResponseEntity<CompanyResponseDto> registerCompany(@Valid @RequestBody CompanyRequestDto dto) {
        CompanyResponseDto response = companyService.createCompany(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //ACTIVAR CUENTA
    @PostMapping("/activate")
    public ResponseEntity<Map<String, String>> activateCompany(@RequestParam String email, @RequestParam String code) {
        companyService.activateCompany(email, code);
        return ResponseEntity.ok(Map.of("message", "¡Cuenta activada exitosamente! Ya puedes iniciar sesión."));
    }

    //LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginCompany(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        String message = companyService.loginCompany(email, password);
        String token = jwtUtils.generateToken(email);

        return ResponseEntity.ok(Map.of(
                "message", message,
                "token", token
        ));
    }

    //FILTRAR Y LISTAR EMPRESAS
    @PostMapping("/filter")
    public ResponseEntity<List<CompanyResponseDto>> filterCompanies(@RequestBody CompanyFilterDto dto) {
        List<CompanyResponseDto> companies = companyService.filterCompanies(dto);
        return ResponseEntity.ok(companies);
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable Long id) {
        CompanyResponseDto response = companyService.findById(id);
        return ResponseEntity.ok(response);
    }

    //ACTUALIZAR DATOS
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable Long id, @RequestBody CompanyUpdateDto dto) {
        CompanyResponseDto response = companyService.updateCompany(id, dto);
        return ResponseEntity.ok(response);
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok(Map.of("message", "Empresa desactivada correctamente"));
    }
}
