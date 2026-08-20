package com.nova.talentnova.repository;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    // --- VALIDACIONES DE DUPLICADOS (PARA CREAR) ---
    boolean existsByMobilePhone(String mobilePhone);
    boolean existsByPersonalEmail(String personalEmail);
    boolean existsByDocumentNumber(String documentNumber);

    // --- VALIDACIONES DE DUPLICADOS PARA ACTUALIZAR ---
    boolean existsByMobilePhoneAndIdNot(String mobilePhone, Long id);
    boolean existsByPersonalEmailAndIdNot(String personalEmail, Long id);

    // Búsquedas directas
    Optional<Employee> findByCorporateEmail(String corporateEmail);
    List<Employee> findByCompanyId(Long companyId);

    //FILTROS AVANZADOS PARA EL LISTADO DE EMPLEADOS
    @Query("""
            SELECT e 
            FROM Employee e 
            WHERE (:companyId IS NULL OR e.company.id = :companyId)
            AND (:id IS NULL OR e.id = :id)
            AND (:corporateEmail IS NULL OR e.corporateEmail LIKE %:corporateEmail%) 
            AND (:status IS NULL OR e.status = :status) 
            AND (:workShiftId IS NULL OR e.workShift.id = :workShiftId) 
            AND (:departamentId IS NULL OR e.department.id = :departamentId) 
            AND (:jobPositionId IS NULL OR e.jobPosition.id = :jobPositionId)
            """)
    List<Employee> filterEmployees(
            @Param("companyId") Long companyId,
            @Param("id") Long id,
            @Param("corporateEmail") String corporateEmail,
            @Param("status") GeneralStatus status,
            @Param("workShiftId") Long workShiftId,
            @Param("departamentId") Long departamentId,
            @Param("jobPositionId") Long jobPositionId
    );
}