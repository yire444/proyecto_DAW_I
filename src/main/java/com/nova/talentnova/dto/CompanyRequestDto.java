package com.nova.talentnova.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDto {

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(max = 150, message = "Ingrese como máximo 150 caracteres")
    private String nameCompany;

    @NotBlank(message = "El RUC es obligatorio")
    @Pattern(regexp = "^[0-9]{11}$", message = "El RUC debe tener exactamente 11 dígitos")
    private String ruc;

    @NotBlank(message = "El nombre del titular es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre del titular solo debe contener letras")
    private String nameHolder;

    @NotBlank(message = "El apellido del titular es obligatorio")
    @Size(max = 150, message = "El apellido no puede superar los 150 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido del titular solo debe contener letras")
    private String lastNameHolder;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(min = 8, max = 12, message = "El número de documento debe tener entre 8 y 12 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El número de documento solo debe contener números")
    private String documentNumber;

    @NotBlank(message = "El correo de la empresa es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    private String emailCompany;

    @NotBlank(message = "El teléfono o celular es obligatorio")
    @Pattern(regexp = "^[0-9]{9}$", message = "El número de celular debe tener exactamente 9 dígitos")
    private String phoneCompany;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotBlank(message = "Debe confirmar la contraseña")
    private String confirmPassword;

    @NotNull(message = "Debe seleccionar un tipo de documento")
    private Long documentTypeId;

    @NotNull(message = "Debe seleccionar un tipo de plan")
    private Long planTypeId;

    @NotNull(message = "Debe seleccionar un ciclo de facturación")
    private Long billingCycleId;
}