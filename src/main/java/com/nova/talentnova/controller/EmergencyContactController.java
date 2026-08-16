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
@RequestMapping("/api/employees/{employeeId}/emergency-contacts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final IEmergencyContactService contactService;

    // 1. LISTAR CONTACTOS DEL EMPLEADO
    @GetMapping
    public ResponseEntity<List<EmergencyContactResponseDto>> getContactsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(contactService.findByEmployeeId(employeeId));
    }

    //REGISTRAR
    @PostMapping
    public ResponseEntity<EmergencyContactResponseDto> registerContact(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmergencyContactRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contactService.registerContact(employeeId, requestDto));
    }

    //ACTUALIZAR
    @PutMapping("/{contactId}")
    public ResponseEntity<EmergencyContactResponseDto> updateContact(
            @PathVariable Long employeeId,
            @PathVariable Long contactId,
            @Valid @RequestBody EmergencyContactRequestDto requestDto) {
        return ResponseEntity.ok(contactService.updateContact(contactId, requestDto));
    }

    //ELIMINAR
    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContact(
            @PathVariable Long employeeId,
            @PathVariable Long contactId) {
        contactService.deleteContact(contactId);
        return ResponseEntity.noContent().build();
    }
}