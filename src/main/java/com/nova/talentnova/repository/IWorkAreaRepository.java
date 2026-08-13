package com.nova.talentnova.repository;

import com.nova.talentnova.model.WorkArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IWorkAreaRepository extends JpaRepository<WorkArea, Integer> {

    List<WorkArea> findByStatusTrue();

    Optional<WorkArea> findByNameAndStatusTrue(String name);
}