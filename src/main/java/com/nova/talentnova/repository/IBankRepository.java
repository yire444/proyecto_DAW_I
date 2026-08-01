package com.nova.talentnova.repository;

import com.nova.talentnova.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBankRepository extends JpaRepository<Bank, Integer> {
}