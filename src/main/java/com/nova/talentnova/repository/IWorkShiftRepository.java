package com.nova.talentnova.repository;

import com.nova.talentnova.model.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWorkShiftRepository extends JpaRepository<WorkShift, Long> {

    Optional<WorkShift> findByCompanyIdAndName(Long companyId, String name);
}