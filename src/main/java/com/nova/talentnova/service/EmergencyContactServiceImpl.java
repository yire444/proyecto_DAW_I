package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmergencyContactRequestDto;
import com.nova.talentnova.dto.EmergencyContactResponseDto;
import com.nova.talentnova.mapper.EmergencyContactMapper;
import com.nova.talentnova.model.EmergencyContact;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.repository.IEmergencyContactRepository;
import com.nova.talentnova.repository.IEmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmergencyContactServiceImpl implements IEmergencyContactService {

    private final IEmergencyContactRepository contactRepository;
    private final IEmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmergencyContactResponseDto> findByEmployeeId(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EntityNotFoundException("Empleado no encontrado con ID: " + employeeId);
        }
        return contactRepository.findByEmployeeId(employeeId).stream()
                .map(EmergencyContactMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmergencyContactResponseDto registerContact(Long employeeId, EmergencyContactRequestDto requestDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + employeeId));

        EmergencyContact contact = EmergencyContactMapper.toEntity(requestDto, employee);
        EmergencyContact savedContact = contactRepository.save(contact);

        return EmergencyContactMapper.toResponseDto(savedContact);
    }

    @Override
    @Transactional
    public EmergencyContactResponseDto updateContact(Long id, EmergencyContactRequestDto requestDto) {
        EmergencyContact contact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contacto de emergencia no encontrado con ID: " + id));

        contact.setMobilePhone(requestDto.getMobilePhone());
        contact.setAddress(requestDto.getAddress());

        EmergencyContact updatedContact = contactRepository.save(contact);
        return EmergencyContactMapper.toResponseDto(updatedContact);
    }

    @Override
    @Transactional
    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new EntityNotFoundException("Contacto de emergencia no encontrado con ID: " + id);
        }
        contactRepository.deleteById(id);
    }
}