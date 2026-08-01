package com.nova.talentnova.repository;

import com.nova.talentnova.model.PensionScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPensionSchemeRepository extends JpaRepository<PensionScheme, Integer> {
}