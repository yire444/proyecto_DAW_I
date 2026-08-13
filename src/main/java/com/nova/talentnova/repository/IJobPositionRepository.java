package com.nova.talentnova.repository;

import com.nova.talentnova.model.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IJobPositionRepository extends JpaRepository<JobPosition, Integer> {

    List<JobPosition> findByStatusTrue();

    Optional<JobPosition> findByNameAndStatusTrue(String name);

    @Query("""
        SELECT j
        FROM JobPosition j
        JOIN FETCH j.workArea
        WHERE (:id IS NULL OR j.id = :id)
        AND (:name IS NULL OR j.name LIKE %:name%)
        AND (:status IS NULL OR j.status = :status)
        AND (:workAreaId IS NULL OR j.workArea.id = :workAreaId)
        """)

    List<JobPosition> searchJobPositions(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("status") Boolean status,
            @Param("workAreaId") Integer workAreaId
    );
}