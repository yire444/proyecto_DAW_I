package com.nova.talentnova.repository;

import com.nova.talentnova.model.InsuranceScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInsuranceSchemeRepository extends JpaRepository<InsuranceScheme, Long> {

    Optional<InsuranceScheme> findByName(String name);
}