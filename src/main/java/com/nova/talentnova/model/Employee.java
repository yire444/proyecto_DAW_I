package com.nova.talentnova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_employees")
@DynamicInsert
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, length = 1)
    private Character gender; // 'F' o 'M'

    @Column(length = 150)
    private String address;

    @Column(nullable = false, length = 15, unique = true)
    private String mobilePhone;

    @Column(nullable = false, length = 50, unique = true)
    private String personalEmail;

    @Column(length = 50, unique = true)
    private String corporateEmail;

    @Column(nullable = false, length = 12, unique = true)
    private String documentNumber;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documentTypeId", nullable = false)
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobPositionId", nullable = false)
    private JobPosition jobPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamentId", nullable = false)
    private Departament departament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractTypeId", nullable = false)
    private ContractType contractType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workShiftId", nullable = false)
    private WorkShift workShift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insuranceSchemeId", nullable = false)
    private InsuranceScheme insuranceScheme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pensionSchemeId", nullable = false)
    private PensionScheme pensionScheme;
}