package com.nova.talentnova.service;

import com.nova.talentnova.dto.PayrollRequestDto;
import com.nova.talentnova.dto.PayrollResponseDto;

import java.util.List;

public interface IPayrollService {

    // Registrar pago
    PayrollResponseDto createPayroll(PayrollRequestDto requestDto);

    // Listar pagos de una empresa específica
    List<PayrollResponseDto> getPayrollsByCompany(Long companyId);

    // Listar pagos de un empleado específico
    List<PayrollResponseDto> getPayrollsByEmployee(Long employeeId);

    // Eliminar un registro de pago
    void deletePayroll(Long id);
}