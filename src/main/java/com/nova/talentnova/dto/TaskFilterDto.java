package com.nova.talentnova.dto;

import com.nova.talentnova.TaskPriority;
import com.nova.talentnova.TaskStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskFilterDto {
    private Long id;
    private String title;
    private LocalDate dueDate;
    private TaskPriority priority;
    private TaskStatus status;
}