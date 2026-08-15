package com.nova.talentnova.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeBankAccountRequestDto {

    @NotNull(message = "El ID del empleado es obligatorio")
    private Long employeeId;

    @NotNull(message = "Seleccione el tipo de banco")
    private Long bankId;

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(max = 20, message = "El número de cuenta no puede tener más de 20 caracteres")
    private String accountNumber;

    @NotBlank(message = "El número CCI es obligatorio")
    @Size(min = 20, max = 20, message = "El CCI no puede tener más 20 caracteres")
    private String cciNumber;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String accountType;

    private Boolean isSalaryAccount = true;
}