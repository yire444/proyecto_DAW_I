package com.nova.talentnova.repository;

import com.nova.talentnova.model.InsuranceScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInsuranceSchemeRepository extends JpaRepository<InsuranceScheme, Integer> {
}