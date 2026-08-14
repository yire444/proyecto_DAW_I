package com.nova.talentnova.repository;

import com.nova.talentnova.model.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContractTypeRepository extends JpaRepository<ContractType, Long> {

    Optional<ContractType> findByName(String name);
}