package com.nova.talentnova.model;

import com.nova.talentnova.LicenseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_license_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicenseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "license_id", nullable = false)
    private SoftwareLicense license;

    @Column(name = "request_date", nullable = false, updatable = false)
    private LocalDateTime requestDate = LocalDateTime.now();

    @Column(name = "justification", length = 255)
    private String justification;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private LicenseStatus status = LicenseStatus.PENDING;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;
}