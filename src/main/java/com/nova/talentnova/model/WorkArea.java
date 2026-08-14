package com.nova.talentnova.model;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.controller.GeneralStatusBooleanConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_work_area")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Convert(converter = GeneralStatusBooleanConverter.class)
    @Column(name = "status", nullable = false)
    private GeneralStatus status = GeneralStatus.ACTIVE;
}