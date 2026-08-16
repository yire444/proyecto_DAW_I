package com.nova.talentnova.model;

import com.nova.talentnova.PayrollStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_payroll")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "period_start_date", nullable = false)
    private LocalDate periodStartDate;

    @Column(name = "period_end_date", nullable = false)
    private LocalDate periodEndDate;

    @Column(name = "base_salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal baseSalary;

    @Column(name = "bonuses", precision = 10, scale = 2)
    private BigDecimal bonuses = BigDecimal.ZERO;

    @Column(name = "deductions", precision = 10, scale = 2)
    private BigDecimal deductions = BigDecimal.ZERO;

    @Column(name = "net_salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal netSalary;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod = "Transferencia";

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private PayrollStatus status = PayrollStatus.PENDING;

    @Column(name = "notes", length = 250)
    private String notes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}