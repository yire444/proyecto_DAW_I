package com.nova.talentnova.repository;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.model.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IJobPositionRepository extends JpaRepository<JobPosition, Long> {

    // Listar filtrando por estado
    List<JobPosition> findByStatus(GeneralStatus status);

    // Buscar por nombre y estado
    Optional<JobPosition> findByNameAndStatus(String name, GeneralStatus status);

    // Consulta avanzada para filtros
    @Query("""
        SELECT j
        FROM JobPosition j
        JOIN FETCH j.workArea
        WHERE (:id IS NULL OR j.id = :id)
        AND (:name IS NULL OR LOWER(j.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:status IS NULL OR j.status = :status)
        AND (:workAreaId IS NULL OR j.workArea.id = :workAreaId)
        """)
    List<JobPosition> searchJobPositions(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("status") GeneralStatus status,
            @Param("workAreaId") Long workAreaId
    );
}