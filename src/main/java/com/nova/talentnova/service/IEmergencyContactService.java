package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmergencyContactRequestDto;
import com.nova.talentnova.dto.EmergencyContactResponseDto;

import java.util.List;

public interface IEmergencyContactService {

    // LISTAR POR ID
    List<EmergencyContactResponseDto> findByEmployeeId(Long employeeId);

    // REGISTRAR
    EmergencyContactResponseDto registerContact(Long employeeId, EmergencyContactRequestDto requestDto);

    // ACTUALIZAR
    EmergencyContactResponseDto updateContact(Long id, EmergencyContactRequestDto requestDto);

    // ELIMINAR
    void deleteContact(Long id);
}