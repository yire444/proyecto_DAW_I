package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmployeeFilterDto;
import com.nova.talentnova.dto.EmployeeProfileUpdateDto;
import com.nova.talentnova.dto.EmployeeRequestDto;
import com.nova.talentnova.dto.EmployeeResponseDto;
import com.nova.talentnova.dto.EmployeeUpdateDto;

import java.util.List;

public interface IEmployeeService {

    // LISTAR CON FILTROS
    List<EmployeeResponseDto> filterEmployees(EmployeeFilterDto filter);

    // BUSCAR POR ID
    EmployeeResponseDto findById(Long id);

    // REGISTRAR
    EmployeeResponseDto registerEmployee(EmployeeRequestDto requestDto);

    // ACTIVAR CUENTA
    void activateEmployee(String corporateEmail, String token);

    // LOGIN EMPLEADO
    EmployeeResponseDto loginEmployee(String corporateEmail, String password);

    // ACTUALIZAR (ADMIN)
    EmployeeResponseDto updateEmployee(Long id, EmployeeUpdateDto updateDto);

    // ACTUALIZAR PERFIL (EMPLEADO)
    EmployeeResponseDto updateEmployeeProfile(Long id, EmployeeProfileUpdateDto dto);

    // ELIMINAR / DESACTIVAR
    void deleteEmployee(Long id);
}