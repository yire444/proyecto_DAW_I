package com.nova.talentnova.model;

import com.nova.talentnova.LicenseAssignmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_assigned_license")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignedLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "license_id", nullable = false)
    private SoftwareLicense softwareLicense;

    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate = LocalDate.now();

    @Column(name = "revoked_date")
    private LocalDate revokedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private LicenseAssignmentStatus status;

}