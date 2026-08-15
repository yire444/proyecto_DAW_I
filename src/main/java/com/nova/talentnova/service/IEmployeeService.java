package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmployeeFilterDto;
import com.nova.talentnova.dto.EmployeeRequestDto;
import com.nova.talentnova.dto.EmployeeResponseDto;

import java.util.List;

public interface IEmployeeService {

    // LISTAR CON FILTROS
    List<EmployeeResponseDto> filterEmployees(EmployeeFilterDto filter);

    // BUSCAR POR ID
    EmployeeResponseDto findById(Long id);

    // REGISTRAR
    EmployeeResponseDto registerEmployee(EmployeeRequestDto requestDto);

    // ACTUALIZAR
    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto);

    // ELIMINAR
    void deleteEmployee(Long id);
}