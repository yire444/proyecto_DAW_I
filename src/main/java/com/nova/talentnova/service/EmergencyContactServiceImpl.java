package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmergencyContactRequestDto;
import com.nova.talentnova.dto.EmergencyContactResponseDto;
import com.nova.talentnova.mapper.EmergencyContactMapper;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.EmergencyContact;
import com.nova.talentnova.repository.IEmergencyContactRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmergencyContactServiceImpl implements IEmergencyContactService {

    private final IEmergencyContactRepository emergencyContactRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //LISTAR
    @Override
    @Transactional(readOnly = true)
    public List<EmergencyContactResponseDto> findByEmployeeId(Integer employeeId) {
        List<EmergencyContact> contacts = emergencyContactRepository.findByEmployeeId(employeeId);
        return contacts.stream()
                .map(EmergencyContactMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    //REGISTRAR
    @Override
    @Transactional
    public EmergencyContactResponseDto registerContact(Integer employeeId, EmergencyContactRequestDto requestDto) {
        EmergencyContact contact = EmergencyContactMapper.toEntity(requestDto);

        contact.setEmployee(entityManager.getReference(Employee.class, employeeId));

        EmergencyContact saved = emergencyContactRepository.save(contact);
        return EmergencyContactMapper.toResponseDto(saved);
    }

    //ACTUALIZAR
    @Override
    @Transactional
    public EmergencyContactResponseDto updateContact(Integer id, EmergencyContactRequestDto requestDto) {
        EmergencyContact existingContact = emergencyContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto de emergencia no encontrado con el ID: " + id));

        existingContact.setRelationShip(requestDto.getRelationShip());
        existingContact.setMobilePhone(requestDto.getMobilePhone());
        existingContact.setAddress(requestDto.getAddress());

        EmergencyContact updated = emergencyContactRepository.save(existingContact);
        return EmergencyContactMapper.toResponseDto(updated);
    }

    //ELIMINAR
    @Override
    @Transactional
    public void deleteContact(Integer id) {
        if (!emergencyContactRepository.existsById(id)) {
            throw new RuntimeException("Contacto de emergencia no encontrado con el ID: " + id);
        }
        emergencyContactRepository.deleteById(id);
    }
}