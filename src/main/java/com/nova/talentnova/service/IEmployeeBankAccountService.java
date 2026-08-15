package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmployeeBankAccountRequestDto;
import com.nova.talentnova.dto.EmployeeBankAccountResponseDto;

import java.util.List;

public interface IEmployeeBankAccountService {

    //LISTAR SEGÚN EL EMPLEADO CORRESPONDIENTE
    List<EmployeeBankAccountResponseDto> findByEmployeeId(Long employeeId);

    //REGISTRAR
    EmployeeBankAccountResponseDto registerAccount(Long employeeId, EmployeeBankAccountRequestDto requestDto);

    //ELIMINAR
    void deleteAccount(Long id);
}