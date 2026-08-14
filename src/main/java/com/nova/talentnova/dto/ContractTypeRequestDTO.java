package com.nova.talentnova.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractTypeRequestDTO {

    @NotBlank(message = "El nombre del tipo de contrato es obligatorio")
    @Size(max = 30, message = "El nombre no puede superar los 30 caracteres")
    private String name;
}