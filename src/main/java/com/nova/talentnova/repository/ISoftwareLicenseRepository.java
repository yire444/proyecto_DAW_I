package com.nova.talentnova.repository;

import com.nova.talentnova.LicenseType;
import com.nova.talentnova.model.SoftwareLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISoftwareLicenseRepository extends JpaRepository<SoftwareLicense, Long> {

    //LISTAR POR EMPRESA
    List<SoftwareLicense> findByCompanyId(Long companyId);

    //FILTRAR
    @Query("""
        SELECT s 
        FROM SoftwareLicense s
        WHERE s.companyId = :companyId
        AND (:id IS NULL OR s.id = :id)
        AND (:softwareName IS NULL OR s.softwareName LIKE CONCAT('%', :softwareName, '%'))
        AND (:provider IS NULL OR s.provider LIKE CONCAT('%', :provider, '%'))
        AND (:licenseType IS NULL OR s.licenseType = :licenseType)
    """)
    List<SoftwareLicense> filterLicenses(
            @Param("companyId") Long companyId,
            @Param("id") Long id,
            @Param("softwareName") String softwareName,
            @Param("provider") String provider,
            @Param("licenseType") LicenseType licenseType
    );
}