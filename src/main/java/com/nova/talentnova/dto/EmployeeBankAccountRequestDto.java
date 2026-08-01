package com.nova.talentnova.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeBankAccountRequestDto {

    @NotNull(message = "Seleccione el tipo de banco ")
    private Integer bankId;

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(min = 10, max = 20, message = "El número de cuenta debe tener entre 10 y 20 caracteres")
    private String accountNumber;

    @NotBlank(message = "El número de CCI es obligatorio")
    @Size(min = 20, max = 20, message = "El CCI debe tener exactamente 20 caracteres")
    private String cciNumber;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Pattern(regexp = "^(Ahorros|Corriente)$", message = "El tipo de cuenta debe ser 'Ahorros' o 'Corriente'")
    private String accountType;

    private Boolean isSalaryAccount;
}