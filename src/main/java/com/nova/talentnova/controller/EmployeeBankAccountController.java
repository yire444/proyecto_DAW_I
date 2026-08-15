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
@RequestMapping("/api/employees/{employeeId}/bank-accounts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmployeeBankAccountController {

    private final IEmployeeBankAccountService bankAccountService;

    //LISTAR CUENTAS DEL EMPLEADO
    @GetMapping
    public ResponseEntity<List<EmployeeBankAccountResponseDto>> listAccount(@PathVariable Long employeeId) {
        List<EmployeeBankAccountResponseDto> accounts = bankAccountService.findByEmployeeId(employeeId);
        return ResponseEntity.ok(accounts);
    }

    //REGISTRAR
    @PostMapping
    public ResponseEntity<EmployeeBankAccountResponseDto> registerAccount(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeBankAccountRequestDto requestDto) {
        EmployeeBankAccountResponseDto newAccount = bankAccountService.registerAccount(employeeId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }

    // 3. ELIMINAR (BORRADO LÓGICO) UNA CUENTA BANCARIA POR SU ID
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable Long employeeId,
            @PathVariable Long accountId) {
        bankAccountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}