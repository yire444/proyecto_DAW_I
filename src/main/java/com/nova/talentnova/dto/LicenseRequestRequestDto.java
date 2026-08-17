package com.nova.talentnova.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicenseRequestRequestDto {

    @NotNull(message = "Seleccionar la licencia es obligatorio")
    private Long licenseId;

    @NotBlank(message = "La justificación es obligatoria")
    @Size(max = 255, message = "La justificación no puede superar los 255 caracteres")
    private String justification;
}