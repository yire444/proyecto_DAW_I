package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmployeeFilterDto;
import com.nova.talentnova.dto.EmployeeRequestDto;
import com.nova.talentnova.dto.EmployeeResponseDto;

import java.util.List;

public interface IEmployeeService {

    //LISTAR TODOS
    List<EmployeeResponseDto> filterEmployees(EmployeeFilterDto filter);

    //BUSCAR POR ID
    EmployeeResponseDto findById(Integer id);

    //REGISTRAR
    EmployeeResponseDto registerEmployee(EmployeeRequestDto requestDto);

    //ACTUALIZAR
    EmployeeResponseDto updateEmployee(Integer id, EmployeeRequestDto requestDto);

    //ELIMINAR
    void deleteEmployee(Integer id);
}