package com.nova.talentnova.model;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.controller.GeneralStatusBooleanConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "gender", nullable = false, length = 1)
    private Character gender; // 'F' o 'M'

    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @Column(name = "mobile_phone", nullable = false, unique = true, length = 15)
    private String mobilePhone;

    @Column(name = "personal_email", nullable = false, unique = true, length = 150)
    private String personalEmail;

    @Column(name = "corporate_email", unique = true, length = 50)
    private String corporateEmail;

    @Column(name = "document_number", nullable = false, unique = true, length = 12)
    private String documentNumber;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salary = BigDecimal.ZERO;

    @Convert(converter = GeneralStatusBooleanConverter.class)
    @Column(nullable = false)
    private GeneralStatus status = GeneralStatus.ACTIVE;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // --- RELACIONES CON EMPRESA Y CATÁLOGOS ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_position_id", nullable = false)
    private JobPosition jobPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private WorkArea department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_type_id", nullable = false)
    private ContractType contractType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_shift_id", nullable = false)
    private WorkShift workShift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_scheme_id", nullable = false)
    private InsuranceScheme insuranceScheme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pension_scheme_id", nullable = false)
    private PensionScheme pensionScheme;
}