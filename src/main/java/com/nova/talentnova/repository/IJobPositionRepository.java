package com.nova.talentnova.repository;

import com.nova.talentnova.model.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IJobPositionRepository extends JpaRepository<JobPosition, Integer> {

    List<JobPosition> findByStatusTrue();

    Optional<JobPosition> findByNameAndStatusTrue(String name);
}