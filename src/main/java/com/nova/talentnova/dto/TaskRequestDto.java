package com.nova.talentnova.dto;

import com.nova.talentnova.TaskPriority;
import jakarta.validation.constraints.FutureOrPresent;
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
public class TaskRequestDto {

    @NotBlank(message = "El título de la tarea es obligatorio")
    @Size(max = 100, message = "El título no puede superar los 100 caracteres")
    private String title;

    @NotBlank(message = "La descripción de la tarea es obligatoria")
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String description;

    @NotNull(message = "La fecha límite es obligatoria")
    private LocalDate dueDate;

    @NotNull(message = "La prioridad es obligatoria")
    private TaskPriority priority;

    @NotNull(message = "Debe asignar un empleado responsable")
    private Long employeeId;
}