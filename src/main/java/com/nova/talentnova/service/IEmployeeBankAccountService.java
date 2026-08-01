package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmployeeBankAccountRequestDto;
import com.nova.talentnova.dto.EmployeeBankAccountResponseDto;

import java.util.List;

public interface IEmployeeBankAccountService {

    //LISTAR
    List<EmployeeBankAccountResponseDto> findByEmployeeId(Integer employeeId);

    //REGISTRAR
    EmployeeBankAccountResponseDto registerAccount(Integer employeeId, EmployeeBankAccountRequestDto requestDto);

    //ELIMINAR
    void deleteAccount(Integer id);
}