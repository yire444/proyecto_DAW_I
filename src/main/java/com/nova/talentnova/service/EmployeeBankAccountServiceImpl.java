package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmployeeBankAccountRequestDto;
import com.nova.talentnova.dto.EmployeeBankAccountResponseDto;
import com.nova.talentnova.mapper.EmployeeBankAccountMapper;
import com.nova.talentnova.model.Bank;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.EmployeeBankAccount;
import com.nova.talentnova.repository.IEmployeeBankAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeBankAccountServiceImpl implements IEmployeeBankAccountService {

    private final IEmployeeBankAccountRepository bankAccountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeBankAccountResponseDto> findByEmployeeId(Integer employeeId) {
        List<EmployeeBankAccount> accounts = bankAccountRepository.findByEmployeeId(employeeId);
        return accounts.stream()
                .map(EmployeeBankAccountMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeBankAccountResponseDto registerAccount(Integer employeeId, EmployeeBankAccountRequestDto requestDto) {
        // Validar si el número de cuenta ya existe
        if (bankAccountRepository.existsByAccountNumber(requestDto.getAccountNumber())) {
            throw new RuntimeException("El número de cuenta ya se encuentra registrado.");
        }
        // Validar si el número de CCI ya existe
        if (bankAccountRepository.existsByCciNumber(requestDto.getCciNumber())) {
            throw new RuntimeException("El número de CCI ya se encuentra registrado.");
        }

        EmployeeBankAccount account = EmployeeBankAccountMapper.toEntity(requestDto);

        // Asociar empleado y banco usando getReference (sin hacer SELECTs innecesarios)
        account.setEmployee(entityManager.getReference(Employee.class, employeeId));
        account.setBank(entityManager.getReference(Bank.class, requestDto.getBankId()));

        EmployeeBankAccount saved = bankAccountRepository.save(account);
        return EmployeeBankAccountMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void deleteAccount(Integer id) {
        EmployeeBankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta bancaria no encontrada con el ID: " + id));
        
        account.setStatus(false);
        bankAccountRepository.save(account);
    }
}