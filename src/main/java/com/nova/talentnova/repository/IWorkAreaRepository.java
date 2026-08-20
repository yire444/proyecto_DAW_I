package com.nova.talentnova.repository;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.model.WorkArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IWorkAreaRepository extends JpaRepository<WorkArea, Long> {

    //LISTAR POR EMPRESA
    List<WorkArea> findByCompanyId(Long companyId);

    // Para listar filtrando por estado (ej. GeneralStatus.ACTIVE)
    List<WorkArea> findByStatus(GeneralStatus status);

    // Para buscar por nombre y estado
    Optional<WorkArea> findByNameAndStatus(String name, GeneralStatus status);
}