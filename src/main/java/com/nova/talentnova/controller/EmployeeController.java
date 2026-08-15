package com.nova.talentnova.controller;

import com.nova.talentnova.dto.EmployeeFilterDto;
import com.nova.talentnova.dto.EmployeeRequestDto;
import com.nova.talentnova.dto.EmployeeResponseDto;
import com.nova.talentnova.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

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

    // REGISTRAR
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> registerEmployee(@Valid @RequestBody EmployeeRequestDto requestDto) {
        EmployeeResponseDto newEmployee = employeeService.registerEmployee(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto requestDto) { // Cambiado a Long
        EmployeeResponseDto updatedEmployee = employeeService.updateEmployee(id, requestDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}