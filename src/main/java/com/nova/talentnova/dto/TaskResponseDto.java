package com.nova.talentnova.dto;

import com.nova.talentnova.TaskPriority;
import com.nova.talentnova.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;
    private TaskStatus status;
    private LocalDateTime createdAt;

    private Long projectId;
    private String projectName;

    private Long employeeId;
    private String employeeName;
}