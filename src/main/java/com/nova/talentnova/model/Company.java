package com.nova.talentnova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_company")
    private String name;

    @Column(name = "RUC")
    private String ruc;

    @Column(name = "name_holder")
    private String nameHoler;

    @Column(name = "last_name_holder")
    private String lastNameHolder;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "email_company")
    private String emailCompany;

    @Column(name = "phone_company")
    private String phoneCompany;

    @Column(name = "creation_date")
    private LocalDate createdDate;
}
