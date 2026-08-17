package com.nova.talentnova.repository;

import com.nova.talentnova.LicenseAssignmentStatus;
import com.nova.talentnova.model.AssignedLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAssignedLicenseRepository extends JpaRepository<AssignedLicense, Long> {

    // Listar asignaciones por empresa (navegando a través de la licencia)
    List<AssignedLicense> findBySoftwareLicenseCompanyId(Long companyId);

    @Query("""
    SELECT a 
    FROM AssignedLicense a
    WHERE a.softwareLicense.companyId = :companyId
    AND (:employeeId IS NULL OR a.employee.id = :employeeId)
    AND (:status IS NULL OR a.status = :status)
    """)
    List<AssignedLicense> filterAssignedLicenses(
            @Param("companyId") Long companyId,
            @Param("employeeId") Long employeeId,
            @Param("status") LicenseAssignmentStatus status
    );
    

}