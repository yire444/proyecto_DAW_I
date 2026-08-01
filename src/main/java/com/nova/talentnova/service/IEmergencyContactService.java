package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmergencyContactRequestDto;
import com.nova.talentnova.dto.EmergencyContactResponseDto;

import java.util.List;

public interface IEmergencyContactService {

    // LISTAR POR ID
    List<EmergencyContactResponseDto> findByEmployeeId(Integer employeeId);

    // REGISTRAR
    EmergencyContactResponseDto registerContact(Integer employeeId, EmergencyContactRequestDto requestDto);

    // ACTUALIZAR
    EmergencyContactResponseDto updateContact(Integer id, EmergencyContactRequestDto requestDto);

    // ELIMINAR
    void deleteContact(Integer id);
}