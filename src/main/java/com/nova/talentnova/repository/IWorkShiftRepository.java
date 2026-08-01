package com.nova.talentnova.repository;

import com.nova.talentnova.model.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkShiftRepository extends JpaRepository<WorkShift, Integer> {
}