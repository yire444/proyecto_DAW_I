package com.nova.talentnova.service;

import com.nova.talentnova.TaskStatus;
import com.nova.talentnova.dto.TaskFilterDto;
import com.nova.talentnova.dto.TaskRequestDto;
import com.nova.talentnova.dto.TaskResponseDto;
import com.nova.talentnova.mapper.TaskMapper;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.Project;
import com.nova.talentnova.model.Task;
import com.nova.talentnova.repository.IEmployeeRepository;
import com.nova.talentnova.repository.IProjectRepository;
import com.nova.talentnova.repository.ITaskRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private final ITaskRepository taskRepo;
    private final IProjectRepository projectRepo;
    private final IEmployeeRepository employeeRepo;
    private final TaskMapper taskMap;

    //REGISTRAR
    @Override
    public TaskResponseDto createTask(Long projectId, TaskRequestDto dto) {

        //BUSCAR PROYECTO
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + projectId));

        //BUSCAR EMPLEADO
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + dto.getEmployeeId()));

        Task task = taskMap.toEntity(dto, project, employee);
        
        //SE CREA EN PROGRESO POR DEFAULT
        task.setStatus(TaskStatus.IN_PROGRESS);

        Task savedTask = taskRepo.save(task);
        return taskMap.toResponseDto(savedTask);
    }

    //LISTAR TAREAS POR EMPRESA
    @Override
    public List<TaskResponseDto> getTasksByCompany(Long companyId) {
        return taskRepo.findByProjectCompanyId(companyId).stream()
                .map(taskMap::toResponseDto)
                .toList();
    }

    //LISTAR TAREAS POR PROYECTO
    @Override
    public List<TaskResponseDto> getTasksByProject(Long projectId) {
        return taskRepo.findByProjectId(projectId).stream()
                .map(taskMap::toResponseDto)
                .toList();
    }

    //LISTAR TAREAS POR EMPLEADO
    @Override
    public List<TaskResponseDto> getTasksByEmployee(Long employeeId) {
        return taskRepo.findByEmployeeId(employeeId).stream()
                .map(taskMap::toResponseDto)
                .toList();
    }

    //FILTRAR TAREAS
    @Override
    public List<TaskResponseDto> filterTasks(TaskFilterDto filter) {
        return taskRepo.filterTasks(
                filter.getId(),
                filter.getTitle(),
                filter.getDueDate(),
                filter.getPriority(),
                filter.getStatus()
        ).stream()
         .map(taskMap::toResponseDto)
         .toList();
    }

    //BUSCAR POR ID
    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada con ID: " + id));
        return taskMap.toResponseDto(task);
    }

    //ACTUALIZAR
    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {
        Task existingTask = taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada con ID: " + id));

        //BUSCAR EMPLEADO PARA REASIGNAR TAREA
        if (!existingTask.getEmployee().getId().equals(dto.getEmployeeId())) {
            Employee newEmployee = employeeRepo.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + dto.getEmployeeId()));
            existingTask.setEmployee(newEmployee);
        }

        existingTask.setTitle(dto.getTitle());
        existingTask.setDescription(dto.getDescription());
        existingTask.setDueDate(dto.getDueDate());
        existingTask.setPriority(dto.getPriority());

        Task updatedTask = taskRepo.save(existingTask);
        return taskMap.toResponseDto(updatedTask);
    }

    //ELIMINAR
    @Override
    public void deleteTask(Long id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada con ID: " + id));

        task.setStatus(TaskStatus.CANCELLED);
        taskRepo.save(task);
    }
}