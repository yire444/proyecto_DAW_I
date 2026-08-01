package com.nova.talentnova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_emergency_contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @Column(name = "relationShip", length = 50, nullable = false)
    private String relationShip;

    @Column(name = "mobilePhone", length = 15, nullable = false)
    private String mobilePhone;

    @Column(name = "address", length = 250, nullable = false)
    private String address;
}