package com.nova.talentnova.controller;

import com.nova.talentnova.dto.AuthResponseDto;
import com.nova.talentnova.dto.LoginRequestDto;
import com.nova.talentnova.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/login-employee")
    public ResponseEntity<AuthResponseDto> loginEmployee(@Valid @RequestBody LoginRequestDto request) {
        AuthResponseDto response = authService.loginEmployee(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login-company")
    public ResponseEntity<AuthResponseDto> loginCompany(@Valid @RequestBody LoginRequestDto request) {
        AuthResponseDto response = authService.loginCompany(request);
        return ResponseEntity.ok(response);
    }
}