package com.nova.talentnova.service;

import com.nova.talentnova.dto.PayrollRequestDto;
import com.nova.talentnova.dto.PayrollResponseDto;
import com.nova.talentnova.mapper.PayrollMapper;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.Payroll;
import com.nova.talentnova.repository.IEmployeeRepository;
import com.nova.talentnova.repository.IPayrollRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements IPayrollService {

    private final IPayrollRepository payrollRepository;
    private final IEmployeeRepository employeeRepository;
    private final PayrollMapper payrollMapper;

    //REGISTRAR
    @Override
    public PayrollResponseDto createPayroll(PayrollRequestDto requestDto) {

        //BUSCAR EL EMPLEADO AL QUE SE REGISTRARA EL PAGO
        Employee employee = employeeRepository.findById(requestDto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + requestDto.getEmployeeId()));

        Payroll payroll = payrollMapper.toEntity(requestDto, employee);

        //CALCULAR PAGO DEL EMPLEADO CON SUELDO, BONOS Y REDUCCIONES
        BigDecimal base = payroll.getBaseSalary() != null ? payroll.getBaseSalary() : BigDecimal.ZERO;
        BigDecimal bonuses = payroll.getBonuses() != null ? payroll.getBonuses() : BigDecimal.ZERO;
        BigDecimal deductions = payroll.getDeductions() != null ? payroll.getDeductions() : BigDecimal.ZERO;

        BigDecimal netSalary = base.add(bonuses).subtract(deductions);
        payroll.setNetSalary(netSalary);

        Payroll savedPayroll = payrollRepository.save(payroll);
        return payrollMapper.toResponseDto(savedPayroll);
    }

    //LISTAR PAGOS DE UNA EMPRESA
    @Override
    public List<PayrollResponseDto> getPayrollsByCompany(Long companyId) {
        return payrollRepository.findByEmployeeCompanyId(companyId).stream()
                .map(payrollMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    //LISTAR PAGOS DE UN EMPLEADO
    @Override
    public List<PayrollResponseDto> getPayrollsByEmployee(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId).stream()
                .map(payrollMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    //ELIMINAR PAGO
    @Override
    public void deletePayroll(Long id) {
        if (!payrollRepository.existsById(id)) {
            throw new EntityNotFoundException("Nómina no encontrada con ID: " + id);
        }
        payrollRepository.deleteById(id);
    }
}