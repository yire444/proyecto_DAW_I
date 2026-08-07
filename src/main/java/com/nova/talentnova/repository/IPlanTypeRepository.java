package com.nova.talentnova.repository;

import com.nova.talentnova.model.PlanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanTypeRepository extends JpaRepository<PlanType, Long> {
}
