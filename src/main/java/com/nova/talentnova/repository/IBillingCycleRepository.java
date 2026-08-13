package com.nova.talentnova.repository;

import com.nova.talentnova.model.BillingCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillingCycleRepository extends JpaRepository<BillingCycle, Long> {
}
