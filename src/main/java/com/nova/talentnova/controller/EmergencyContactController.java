package com.nova.talentnova.controller;

import com.nova.talentnova.dto.EmergencyContactRequestDto;
import com.nova.talentnova.dto.EmergencyContactResponseDto;
import com.nova.talentnova.service.IEmergencyContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-contact")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final IEmergencyContactService emergencyContactService;

    //LISTAR
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmergencyContactResponseDto>> findByEmployeeId(@PathVariable Integer employeeId) {
        List<EmergencyContactResponseDto> contacts = emergencyContactService.findByEmployeeId(employeeId);
        return ResponseEntity.ok(contacts);
    }

    //REGISTRAR
    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<EmergencyContactResponseDto> registerContact(
            @PathVariable Integer employeeId,
            @Valid @RequestBody EmergencyContactRequestDto requestDto) {
        
        EmergencyContactResponseDto newContact = emergencyContactService.registerContact(employeeId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newContact);
    }

    //ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<EmergencyContactResponseDto> updateContact(
            @PathVariable Integer id,
            @Valid @RequestBody EmergencyContactRequestDto requestDto) {
        
        EmergencyContactResponseDto updatedContact = emergencyContactService.updateContact(id, requestDto);
        return ResponseEntity.ok(updatedContact);
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        emergencyContactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}