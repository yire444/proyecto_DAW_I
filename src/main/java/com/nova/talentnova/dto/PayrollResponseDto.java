package com.nova.talentnova.dto;

import com.nova.talentnova.PayrollStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayrollResponseDto {

    private Long id;

    private Long employeeId;
    private String employeeFullName;
    private String employeeDocumentNumber;
    private String jobPositionName;

    private LocalDate paymentDate;
    private LocalDate periodStartDate;
    private LocalDate periodEndDate;

    private BigDecimal baseSalary;
    private BigDecimal bonuses;
    private BigDecimal deductions;
    private BigDecimal netSalary;

    private String paymentMethod;
    private PayrollStatus status;
    private String notes;
    private LocalDateTime createdAt;
}