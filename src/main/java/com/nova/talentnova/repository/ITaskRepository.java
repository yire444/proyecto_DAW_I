package com.nova.talentnova.repository;

import com.nova.talentnova.TaskPriority;
import com.nova.talentnova.TaskStatus;
import com.nova.talentnova.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

    //LISTAR TAREAS (JpaRepository ya lo trae por defecto, pero puedes dejarlo explícito)
    List<Task> findByProjectCompanyId(Long companyId);

    //LISTAR TAREAS POR PROYECTO
    List<Task> findByProjectId(Long projectId);

    //LISTAR TAREAS POR EMPLEADO
    List<Task> findByEmployeeId(Long employeeId);

    //FILTROS DINÁMICOS PARA LAS TAREAS
    @Query("""
        SELECT t
        FROM Task t
        WHERE (:id IS NULL OR t.id = :id)
        AND (:title IS NULL OR t.title LIKE CONCAT('%', :title, '%'))
        AND (:dueDate IS NULL OR t.dueDate = :dueDate)
        AND (:priority IS NULL OR t.priority = :priority)
        AND (:status IS NULL OR t.status = :status)
    """)
    List<Task> filterTasks(
            @Param("id") Long id,
            @Param("title") String title,
            @Param("dueDate") LocalDate dueDate,
            @Param("priority") TaskPriority priority,
            @Param("status") TaskStatus status
    );
}