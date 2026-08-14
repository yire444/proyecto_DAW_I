package com.nova.talentnova.controller;

import com.nova.talentnova.dto.EmployeeBankAccountRequestDto;
import com.nova.talentnova.dto.EmployeeBankAccountResponseDto;
import com.nova.talentnova.service.IEmployeeBankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-bank-account")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmployeeBankAccountController {

    private final IEmployeeBankAccountService bankAccountService;

    //LISTAR
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeBankAccountResponseDto>> findByEmployeeId(@PathVariable Integer employeeId) {
        List<EmployeeBankAccountResponseDto> accounts = bankAccountService.findByEmployeeId(employeeId);
        return ResponseEntity.ok(accounts);
    }

    //REGISTRAR
    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeBankAccountResponseDto> registerAccount(
            @PathVariable Integer employeeId,
            @Valid @RequestBody EmployeeBankAccountRequestDto requestDto) {
        
        EmployeeBankAccountResponseDto newAccount = bankAccountService.registerAccount(employeeId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        bankAccountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}