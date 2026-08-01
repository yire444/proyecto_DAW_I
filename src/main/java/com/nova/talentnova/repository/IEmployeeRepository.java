package com.nova.talentnova.repository;

import com.nova.talentnova.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    // --- VALIDACIONES DE DUPLICADOS (PARA CREAR) ---
    boolean existsByMobilePhone(String mobilePhone);
    boolean existsByPersonalEmail(String personalEmail);
    boolean existsByDocumentNumber(String documentNumber);

    // --- VALIDACIONES DE DUPLICADOS CON EXCLUSIÓN (PARA ACTUALIZAR) ---
    // Ignoran el propio ID del empleado para permitir guardar si el correo o teléfono sigue siendo el suyo
    boolean existsByMobilePhoneAndIdNot(String mobilePhone, Integer id);
    boolean existsByPersonalEmailAndIdNot(String personalEmail, Integer id);

    // Búsqueda opcional útil para validaciones internas o autenticación
    Optional<Employee> findByCorporateEmail(String corporateEmail);

    // --- FILTROS AVANZADOS PARA EL LISTADO DE EMPLEADOS ---
    @Query("SELECT e FROM Employee e WHERE " +
           "(:id IS NULL OR e.id = :id) AND " +
           "(:corporateEmail IS NULL OR e.corporateEmail LIKE %:corporateEmail%) AND " +
           "(:status IS NULL OR e.status = :status) AND " +
           "(:workShiftId IS NULL OR e.workShift.id = :workShiftId) AND " +
           "(:departamentId IS NULL OR e.departament.id = :departamentId) AND " +
           "(:jobPositionId IS NULL OR e.jobPosition.id = :jobPositionId)")
    List<Employee> filterEmployees(
            @Param("id") Integer id,
            @Param("corporateEmail") String corporateEmail,
            @Param("status") Boolean status,
            @Param("workShiftId") Integer workShiftId,
            @Param("departamentId") Integer departamentId,
            @Param("jobPositionId") Integer jobPositionId
    );
}