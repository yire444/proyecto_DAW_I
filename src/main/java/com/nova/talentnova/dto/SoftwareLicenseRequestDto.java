package com.nova.talentnova.dto;

import com.nova.talentnova.LicenseType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareLicenseRequestDto {

    @NotBlank(message = "El nombre del software es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String softwareName;

    @NotBlank(message = "El proveedor es obligatorio")
    @Size(max = 50, message = "El proveedor no puede superar los 50 caracteres")
    private String provider;

    @NotNull(message = "El tipo de licencia es obligatorio")
    private LicenseType licenseType;

    @NotNull(message = "El total de llaves es obligatorio")
    @Min(value = 1, message = "Debe registrar al menos 1 llave disponible")
    private Integer totalKeys;

    private LocalDate expirationDate;
}