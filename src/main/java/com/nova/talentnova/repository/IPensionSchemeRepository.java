package com.nova.talentnova.repository;

import com.nova.talentnova.model.PensionScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPensionSchemeRepository extends JpaRepository<PensionScheme, Long> {

    Optional<PensionScheme> findByName(String name);
}