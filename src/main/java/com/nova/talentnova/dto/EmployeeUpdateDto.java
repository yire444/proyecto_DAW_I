package com.nova.talentnova.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EmployeeUpdateDto {

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 150, message = "La dirección no puede tener más de 150 caracteres")
    private String address;

    @NotBlank(message = "El teléfono móvil es obligatorio")
    @Size(max = 15, message = "El teléfono móvil no puede tener más de 15 caracteres")
    private String mobilePhone;

    @NotBlank(message = "El correo personal es obligatorio")
    @Email(message = "El formato del correo personal no es válido")
    @Size(max = 150, message = "El correo personal no puede tener más de 150 caracteres")
    private String personalEmail;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser mayor a cero")
    private BigDecimal salary;

    @NotNull(message = "El puesto de trabajo es obligatorio")
    private Long jobPositionId;

    @NotNull(message = "El departamento es obligatorio")
    private Long departmentId;

    @NotNull(message = "El tipo de contrato es obligatorio")
    private Long contractTypeId;

    @NotNull(message = "El turno de trabajo es obligatorio")
    private Long workShiftId;

    @NotNull(message = "El esquema de seguro es obligatorio")
    private Long insuranceSchemeId;

    @NotNull(message = "El régimen de pensiones es obligatorio")
    private Long pensionSchemeId;
}