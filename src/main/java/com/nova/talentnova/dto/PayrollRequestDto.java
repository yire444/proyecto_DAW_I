package com.nova.talentnova.dto;

import com.nova.talentnova.PayrollStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayrollRequestDto {

    @NotNull(message = "El seleccione el empleado es obligatorio")
    private Long employeeId;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDate paymentDate;

    @NotNull(message = "La fecha de inicio de periodo es obligatoria")
    private LocalDate periodStartDate;

    @NotNull(message = "La fecha de fin de periodo es obligatoria")
    private LocalDate periodEndDate;

    private BigDecimal baseSalary; //SE OBTIENE DEL EMPLEADO

    private BigDecimal bonuses; //AGREGAR EN CASO HUBIERA

    private BigDecimal deductions; //AGREGAR EN CASO HUBIERA

    private String paymentMethod; //"Transferencia", "Efectivo"

    private PayrollStatus status;

    private String notes; //JUSTIFICAR EN CASO DE BONOS O DESCUENTOS
}