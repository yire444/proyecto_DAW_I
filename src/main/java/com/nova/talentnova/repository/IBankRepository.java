package com.nova.talentnova.repository;

import com.nova.talentnova.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByName(String name);
}