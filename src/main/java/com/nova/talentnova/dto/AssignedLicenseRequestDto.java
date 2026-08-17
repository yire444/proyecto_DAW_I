package com.nova.talentnova.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignedLicenseRequestDto {

    @NotNull(message = "El ID del empleado es obligatorio")
    private Long employeeId;
    @NotNull(message = "El ID de la licencia es obligatorio")
    private Long licenseId;
}
