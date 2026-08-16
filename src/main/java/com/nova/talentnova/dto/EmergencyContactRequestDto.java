package com.nova.talentnova.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmergencyContactRequestDto {

    @NotBlank(message = "El nombre del contacto es obligatorio")
    @Size(max = 150, message = "El nombre no puede tener más de 150 caracteres")
    private String name;

    @NotBlank(message = "El parentesco es obligatorio")
    @Size(max = 50, message = "El parentesco no puede tener más de 50 caracteres")
    private String relationship;

    @NotBlank(message = "El teléfono móvil es obligatorio")
    @Size(max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    private String mobilePhone;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 250, message = "La dirección no puede tener más de 250 caracteres")
    private String address;
}