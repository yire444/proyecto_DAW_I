package com.nova.talentnova.service;

import com.nova.talentnova.dto.TaskFilterDto;
import com.nova.talentnova.dto.TaskRequestDto;
import com.nova.talentnova.dto.TaskResponseDto;

import java.util.List;

public interface ITaskService {

    //REGISTRAR TAREA
    TaskResponseDto createTask(Long projectId, TaskRequestDto dto);

    //LISTAR TODAS LAS TAREAS
    List<TaskResponseDto> getTasksByCompany(Long companyId);

    //LISTAR TAREAS POR EMPLEADO
    List<TaskResponseDto> getTasksByEmployee(Long employeeId);

    //LISTAR TAREAS POR PROYECTO
    List<TaskResponseDto> getTasksByProject(Long projectId);

    //FILTRAR TAREAS
    List<TaskResponseDto> filterTasks(TaskFilterDto filter);

    //BUSCAR TAREA POR ID
    TaskResponseDto getTaskById(Long id);

    //ACTUALIZAR TAREA
    TaskResponseDto updateTask(Long id, TaskRequestDto dto);

    //ELIMINAR TAREAS
    void deleteTask(Long id);
}