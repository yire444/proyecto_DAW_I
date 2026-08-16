package com.nova.talentnova.controller;

import com.nova.talentnova.dto.PayrollRequestDto;
import com.nova.talentnova.dto.PayrollResponseDto;
import com.nova.talentnova.service.IPayrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final IPayrollService payrollService;

    //REGISTRAR
    @PostMapping
    public ResponseEntity<PayrollResponseDto> createPayroll(@Valid @RequestBody PayrollRequestDto requestDto) {
        PayrollResponseDto createdPayroll = payrollService.createPayroll(requestDto);
        return new ResponseEntity<>(createdPayroll, HttpStatus.CREATED);
    }

    //LISTAR PAGOS DE UNA EMPRESA
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<PayrollResponseDto>> getPayrollsByCompany(@PathVariable Long companyId) {
        List<PayrollResponseDto> payrolls = payrollService.getPayrollsByCompany(companyId);
        return ResponseEntity.ok(payrolls);
    }

    //LISTAR PAGOS DE UN EMPLEADO
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayrollResponseDto>> getPayrollsByEmployee(@PathVariable Long employeeId) {
        List<PayrollResponseDto> payrolls = payrollService.getPayrollsByEmployee(employeeId);
        return ResponseEntity.ok(payrolls);
    }

    //ELIMINAR PAGO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }
}