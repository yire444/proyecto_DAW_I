package com.nova.talentnova.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDto {

    @Email(message = "Debe proporcionar un correo electrónico válido")
    private String emailCompany;

    @Size(min = 9, max = 9, message = "El número de celular debe tener 9 dígitos")
    private String phoneCompany;

    private Long planTypeId;
    private Long billingCycleId;
}
