package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.PayrollRequestDto;
import com.nova.talentnova.dto.PayrollResponseDto;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.Payroll;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PayrollMapper {

    //ENTIDAD -> DTO
    public PayrollResponseDto toResponseDto(Payroll payroll) {
        if (payroll == null) {return null;}

        //DATOS DEL EMPLADO
        PayrollResponseDto dto = new PayrollResponseDto();
        dto.setId(payroll.getId());

        if (payroll.getEmployee() != null) {
            Employee emp = payroll.getEmployee();
            dto.setEmployeeId(emp.getId());
            dto.setEmployeeFullName(emp.getName() + " " + emp.getLastname());
            dto.setEmployeeDocumentNumber(emp.getDocumentNumber());

            if (emp.getJobPosition() != null) {
                dto.setJobPositionName(emp.getJobPosition().getName());
            }
        }

        dto.setPaymentDate(payroll.getPaymentDate());
        dto.setPeriodStartDate(payroll.getPeriodStartDate());
        dto.setPeriodEndDate(payroll.getPeriodEndDate());
        dto.setBaseSalary(payroll.getBaseSalary());
        dto.setBonuses(payroll.getBonuses());
        dto.setDeductions(payroll.getDeductions());
        dto.setNetSalary(payroll.getNetSalary());
        dto.setPaymentMethod(payroll.getPaymentMethod());
        dto.setStatus(payroll.getStatus());
        dto.setNotes(payroll.getNotes());
        dto.setCreatedAt(payroll.getCreatedAt());

        return dto;
    }

    // DTO -> ENTIDAD
    public Payroll toEntity(PayrollRequestDto requestDto, Employee employee) {
        if (requestDto == null || employee == null) {
            return null;
        }

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setPaymentDate(requestDto.getPaymentDate());
        payroll.setPeriodStartDate(requestDto.getPeriodStartDate());
        payroll.setPeriodEndDate(requestDto.getPeriodEndDate());

        //OBTENER SUELDO DESDE EL EMPLEADO
        payroll.setBaseSalary(employee.getSalary());

        //SI HAY BONOS O DEDUCCIONES
        payroll.setBonuses(requestDto.getBonuses() != null ? requestDto.getBonuses() : BigDecimal.ZERO);
        payroll.setDeductions(requestDto.getDeductions() != null ? requestDto.getDeductions() : BigDecimal.ZERO);

        payroll.setPaymentMethod(requestDto.getPaymentMethod() != null ? requestDto.getPaymentMethod() : "Transferencia");

        if (requestDto.getStatus() != null) {
            payroll.setStatus(requestDto.getStatus());
        }

        payroll.setNotes(requestDto.getNotes());

        return payroll;
    }
}