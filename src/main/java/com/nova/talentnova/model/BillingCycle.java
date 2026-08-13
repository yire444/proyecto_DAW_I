package com.nova.talentnova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_billing_cycle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "months", nullable = false)
    private Integer months;

    @Column(name = "discount", precision = 5, scale = 2)
    private BigDecimal discount;

}
