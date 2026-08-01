package com.nova.talentnova.repository;

import com.nova.talentnova.model.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContractTypeRepository extends JpaRepository<ContractType, Integer> {
}