package com.nova.talentnova.repository;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICompanyRepository extends JpaRepository<Company, Long> {

    //BUSCAR DATOS DE LA EMPRESA
    Optional<Company> findByRuc(String ruc);

    Optional<Company> findByEmailCompany(String emailCompany);

    Optional<Company> findByPhoneCompany(String phoneCompany);

    //VALIDAR DATOS PARA EL ACTUALIZAR
    Optional<Company> findByEmailCompanyAndIdNot(String emailCompany, Long id);

    Optional<Company> findByPhoneCompanyAndIdNot(String phoneCompany, Long id);

    Optional<Company> findByDocumentNumber(String documentNumber);

    //ACTIVAR CUENTA
    Optional<Company> findByEmailCompanyAndVerificationCode(String emailCompany, String verificationCode);

    //LISTAR PARA EL PANEL DE ADMIN
    @Query("""
        SELECT c
        FROM Company c
        JOIN FETCH c.planType
        JOIN FETCH c.billingCycle
        WHERE (:id IS NULL OR c.id = :id)
        AND (:ruc IS NULL OR c.ruc LIKE %:ruc%)
        AND (:name IS NULL OR c.nameCompany LIKE %:name%)
        AND (:email IS NULL OR c.emailCompany LIKE %:email%)
        AND (:phone IS NULL OR c.phoneCompany LIKE %:phone%)
        AND (:status IS NULL OR c.status = :status)
        AND (:planTypeId IS NULL OR c.planType.id = :planTypeId)
        AND (:billingCycleId IS NULL OR c.billingCycle.id = :billingCycleId)
        """)
    List<Company> filterByCompany(
            @Param("id") Long id,
            @Param("ruc") String ruc,
            @Param("name") String nameCompany,
            @Param("email") String emailCompany,
            @Param("phone") String phoneCompany,
            @Param("status") GeneralStatus status,
            @Param("planTypeId") Long planTypeId,
            @Param("billingCycleId") Long billingCycleId
    );
}