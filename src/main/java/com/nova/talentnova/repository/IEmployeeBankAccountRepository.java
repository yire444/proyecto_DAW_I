package com.nova.talentnova.repository;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.model.EmployeeBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeBankAccountRepository extends JpaRepository<EmployeeBankAccount, Long> {

    //BUSCAR CUENTAS POR EMPLEADO
    List<EmployeeBankAccount> findByEmployeeIdAndStatus(Long employeeId, GeneralStatus status);

    //VALIDAR DUPLICADOS POR NUMERO DE CUENTA Y CCI
    boolean existsByAccountNumber(String accountNumber);
    boolean existsByCciNumber(String cciNumber);
}