package com.nova.talentnova.repository;

import com.nova.talentnova.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {

    //LISTAR
    List<EmergencyContact> findByEmployeeId(Long employeeId);
}