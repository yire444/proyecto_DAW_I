package com.nova.talentnova.controller;

import com.nova.talentnova.dto.EmployeeFilterDto;
import com.nova.talentnova.dto.EmployeeProfileUpdateDto;
import com.nova.talentnova.dto.EmployeeRequestDto;
import com.nova.talentnova.dto.EmployeeResponseDto;
import com.nova.talentnova.security.JwtUtils;
import com.nova.talentnova.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nova.talentnova.dto.EmployeeUpdateDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;
    private final JwtUtils jwtUtils;

    // LISTAR CON FILTROS
    @PostMapping("/list-and-filter")
    public ResponseEntity<List<EmployeeResponseDto>> filterEmployees(@RequestBody(required = false) EmployeeFilterDto filter) {
        if (filter == null) {
            filter = new EmployeeFilterDto();
        }
        List<EmployeeResponseDto> employees = employeeService.filterEmployees(filter);
        return ResponseEntity.ok(employees);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> findById(@PathVariable Long id) {
        EmployeeResponseDto employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    //REGISTRAR
    @PostMapping("/register")
    public ResponseEntity<EmployeeResponseDto> registerEmployee(
            @Valid @RequestBody EmployeeRequestDto requestDto
    ) {
        EmployeeResponseDto newEmployee = employeeService.registerEmployee(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    //ACTIVAR CUENTA
    @PostMapping("/activate-employee")
    public ResponseEntity<Map<String, String>> activateAccount(@RequestBody Map<String, String> request) {
        String corporateEmail = request.get("corporateEmail");
        String token = request.get("token");

        employeeService.activateEmployee(corporateEmail, token);
        return ResponseEntity.ok(Map.of("message", "Cuenta activada exitosamente. Ya puedes iniciar sesión."));
    }

    //LOGIN EMPLEADO
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginEmployee(@RequestBody Map<String, String> credentials) {
        String corporateEmail = credentials.get("corporateEmail");
        String password = credentials.get("password");

        EmployeeResponseDto employee = employeeService.loginEmployee(corporateEmail, password);

        String token = jwtUtils.generateToken(corporateEmail, employee.getId());

        return ResponseEntity.ok(Map.of(
                "message", "¡Bienvenido a tu portal de colaborador!",
                "token", token,
                "id", employee.getId()
        ));
    }

    //ACTUALIZAR EMPLEADO
    @PutMapping("/{id}/profile")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeProfile(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeProfileUpdateDto dto) {
        EmployeeResponseDto updatedProfile = employeeService.updateEmployeeProfile(id, dto);
        return ResponseEntity.ok(updatedProfile);
    }

    // ACTUALIZAR EMPLEADO EMPRESA
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeUpdateDto updateDto
    ) {
        EmployeeResponseDto updatedEmployee = employeeService.updateEmployee(id, updateDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}