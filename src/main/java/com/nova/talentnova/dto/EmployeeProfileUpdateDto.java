package com.nova.talentnova.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeProfileUpdateDto {

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

    private String currentPassword;
    private String newPassword;
}