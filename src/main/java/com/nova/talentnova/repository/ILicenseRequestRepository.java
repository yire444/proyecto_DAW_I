package com.nova.talentnova.repository;

import com.nova.talentnova.LicenseStatus;
import com.nova.talentnova.model.LicenseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILicenseRequestRepository extends JpaRepository<LicenseRequest, Long> {

    //LISTAR SOLICITUDES POR EMPRESA
    List<LicenseRequest> findByLicenseCompanyId(Long companyId);

    // FILTRAR
    @Query("""
        SELECT l
        FROM LicenseRequest l
        WHERE l.license.companyId = :companyId
        AND (:id IS NULL OR l.id = :id)
        AND (:employeeId IS NULL OR l.employee.id = :employeeId)
        AND (:status IS NULL OR l.status = :status)
    """)
    List<LicenseRequest> filterRequests(
            @Param("companyId") Long companyId,
            @Param("id") Long id,
            @Param("employeeId") Long employeeId,
            @Param("status") LicenseStatus status
    );
}