package com.nova.talentnova.controller;

import com.nova.talentnova.dto.TaskFilterDto;
import com.nova.talentnova.dto.TaskRequestDto;
import com.nova.talentnova.dto.TaskResponseDto;
import com.nova.talentnova.service.ITaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskController {

    private final ITaskService taskService;

    //REGISTRAR TAREA
    @PostMapping("/project/{projectId}")
    public ResponseEntity<TaskResponseDto> createTask(
            @PathVariable Long projectId,
            @Valid @RequestBody TaskRequestDto requestDto) {
        TaskResponseDto createdTask = taskService.createTask(projectId, requestDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    //LISTAR TAREAS POR EMPRESA
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByCompany(@PathVariable Long companyId) {
        List<TaskResponseDto> tasks = taskService.getTasksByCompany(companyId);
        return ResponseEntity.ok(tasks);
    }

    //LISTAR POR PROYECTO AL QUE PERTENECE
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByProject(@PathVariable Long projectId) {
        List<TaskResponseDto> tasks = taskService.getTasksByProject(projectId);
        return ResponseEntity.ok(tasks);
    }

    //LISTAR TAREAS POR EMPLEADO
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(taskService.getTasksByEmployee(employeeId));
    }

    //FILTRAR TAREAS
    @GetMapping("/filter")
    public ResponseEntity<List<TaskResponseDto>> filterTasks(TaskFilterDto filter) {
        List<TaskResponseDto> tasks = taskService.filterTasks(filter);
        return ResponseEntity.ok(tasks);
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        TaskResponseDto task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    //ACTUALZAR
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDto requestDto) {
        TaskResponseDto updatedTask = taskService.updateTask(id, requestDto);
        return ResponseEntity.ok(updatedTask);
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}