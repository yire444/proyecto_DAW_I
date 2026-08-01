package com.nova.talentnova.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank(message = "El correo corporativo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    private String corporateEmail;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}