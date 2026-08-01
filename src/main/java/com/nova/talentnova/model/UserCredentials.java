package com.nova.talentnova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user_credentials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", nullable = false, unique = true)
    private Employee employee;

    @Column(name = "passwordHash", length = 255, nullable = false)
    private String password;

    @Column(name = "activationToken", length = 100)
    private String activationToken;

    @Column(name = "tokenExpiration")
    private LocalDateTime tokenExpiration;

    @Column(name = "isActivated", nullable = false)
    private Boolean isActivated = true;

    @Column(name = "systemRole", length = 20, nullable = false)
    private String systemRole = "USER";

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}