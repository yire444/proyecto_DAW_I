package com.nova.talentnova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_employee_bank_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankId", nullable = false)
    private Bank bank;

    @Column(name = "accountNumber", length = 20, nullable = false)
    private String accountNumber;

    @Column(name = "cciNumber", length = 20, nullable = false)
    private String cciNumber;

    @Column(name = "accountType", length = 20, nullable = false)
    private String accountType;

    @Column(name = "isSalaryAccount", nullable = false)
    private Boolean isSalaryAccount = true;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}