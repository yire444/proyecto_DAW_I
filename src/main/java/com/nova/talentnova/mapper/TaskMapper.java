package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.TaskRequestDto;
import com.nova.talentnova.dto.TaskResponseDto;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.Project;
import com.nova.talentnova.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    // DTO -> ENTIDAD
    public Task toEntity(TaskRequestDto dto, Project project, Employee employee) {
        if (dto == null) {return null;}

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());
        task.setPriority(dto.getPriority());

        task.setProject(project);
        task.setEmployee(employee);

        return task;
    }

    // ENTIDAD -> DTO
    public TaskResponseDto toResponseDto(Task task) {
        if (task == null) {return null;}

        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setCreatedAt(task.getCreatedAt());

        if (task.getProject() != null) {
            dto.setProjectId(task.getProject().getId());
            dto.setProjectName(task.getProject().getName());
        }

        if (task.getEmployee() != null) {
            dto.setEmployeeId(task.getEmployee().getId());
            dto.setEmployeeName(task.getEmployee().getName());
        }

        return dto;
    }
}