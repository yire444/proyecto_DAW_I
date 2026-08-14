package com.nova.talentnova.model;

import com.nova.talentnova.GeneralStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_company", nullable = false, length = 150)
    private String nameCompany;

    @Column(name = "RUC", nullable = false, unique = true, length = 11)
    private String ruc;

    @Column(name = "name_holder", nullable = false, length = 150)
    private String nameHolder;

    @Column(name = "last_name_holder", nullable = false, length = 150)
    private String lastNameHolder;

    @Column(name = "document_number", nullable = false, length = 12)
    private String documentNumber;

    @Column(name = "email_company", nullable = false, unique = true)
    private String emailCompany;

    @Column(name = "phone_company", nullable = false)
    private String phoneCompany;

    @Column(name = "creation_date")
    private LocalDate createdDate;

    @Column(name = "password",  nullable = false, length = 255)
    private String password;

    @Column(name = "verification_code", length = 6)
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private GeneralStatus status = GeneralStatus.PENDING;

    //TIPO DE DOCUMENTO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    //PLAN SELECCIONADO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_type_id", nullable = false)
    private PlanType planType;

    //TIPO DE FACTURACIÓN
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_cycle_id", nullable = false)
    private BillingCycle billingCycle;
}