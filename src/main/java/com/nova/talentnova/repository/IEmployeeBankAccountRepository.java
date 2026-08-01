package com.nova.talentnova.repository;

import com.nova.talentnova.model.EmployeeBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeBankAccountRepository extends JpaRepository<EmployeeBankAccount, Integer> {

    //LISTAR
    List<EmployeeBankAccount> findByEmployeeId(Integer employeeId);

    //VALIDAR NO REPETIDOS
    boolean existsByAccountNumber(String accountNumber);
    boolean existsByCciNumber(String cciNumber);
}