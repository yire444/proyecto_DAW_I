package com.nova.talentnova.model;

import com.nova.talentnova.LicenseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_software_license")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "software_name", nullable = false, length = 100)
    private String softwareName;

    @Column(name = "provider", nullable = false, length = 50)
    private String provider;

    @Enumerated(EnumType.STRING)
    @Column(name = "license_type", nullable = false, length = 50)
    private LicenseType licenseType;

    @Column(name = "total_keys", nullable = false)
    private Integer totalKeys;

    @Column(name = "available_keys", nullable = false)
    private Integer availableKeys;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}