package com.nova.talentnova.repository;

import com.nova.talentnova.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPayrollRepository extends JpaRepository<Payroll, Long> {

    //LISTAR PAGOS DE UN EMPLEADO ESPECIFICO
    List<Payroll> findByEmployeeId(Long employeeId);

    //LISTAR PAGOS DE EMPLEADOS DE UNA EMPRESA
    List<Payroll> findByEmployeeCompanyId(Long companyId);
}