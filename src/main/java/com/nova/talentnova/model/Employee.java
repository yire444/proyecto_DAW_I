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
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "gender", nullable = false, length = 1)
    private Character gender; // 'F' o 'M'

    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "MOBILE_PHONE", nullable = false, length = 15, unique = true)
    private String mobilePhone;

    @Column(name = "PERSONAL_EMAIL", nullable = false, length = 50, unique = true)
    private String personalEmail;

    @Column(name = "CORPORATE_EMAIL", length = 50, unique = true)
    private String corporateEmail;

    @Column(name = "DOCUMENT_NUMBER", nullable = false, length = 12, unique = true)
    private String documentNumber;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
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