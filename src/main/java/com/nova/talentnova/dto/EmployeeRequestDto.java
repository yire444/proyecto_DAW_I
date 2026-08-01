package com.nova.talentnova.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message="El nombre no puede superar los 50 caracteres")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no puede superar los 50 caracteres")
    private String lastname;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate birthdate;

    @NotNull(message = "El género es obligatorio")
    private Character gender; // 'F' o 'M'

    @Size(max = 150, message = "La dirección no puede superar los 150 caracteres")
    private String address;

    @NotBlank(message = "El teléfono móvil es obligatorio")
    @Size(max = 15, message = "El teléfono no puede superar los 15 caracteres")
    private String mobilePhone;

    @NotBlank(message = "El correo personal es obligatorio")
    @Email(message = "El formato del correo personal no es válido")
    @Size(max = 50, message = "El correo personal no puede superar los 50 caracteres")
    private String personalEmail;

    @NotNull(message = "El tipo de documento es obligatorio")
    private Integer documentTypeId;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 12, message = "El número de documento no puede superar los 12 caracteres")
    private String documentNumber;

    @NotNull(message = "El puesto de trabajo es obligatorio")
    private Integer jobPositionId;

    @NotNull(message = "El departamento es obligatorio")
    private Integer departamentId;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    @NotNull(message = "El salario es obligatorio")
    @DecimalMin(value = "0.00", inclusive = true, message = "El salario debe ser mayor o igual a 0")
    private BigDecimal salary;

    @NotNull(message = "El tipo de contrato es obligatorio")
    private Integer contractTypeId;

    @NotNull(message = "El turno de trabajo es obligatorio")
    private Integer workShiftId;

    @NotNull(message = "El régimen de salud es obligatorio")
    private Integer insuranceSchemeId;

    @NotNull(message = "El régimen de pensiones es obligatorio")
    private Integer pensionSchemeId;
}