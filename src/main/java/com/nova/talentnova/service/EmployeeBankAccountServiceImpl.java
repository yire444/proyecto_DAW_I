package com.nova.talentnova.service;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.dto.EmployeeBankAccountRequestDto;
import com.nova.talentnova.dto.EmployeeBankAccountResponseDto;
import com.nova.talentnova.mapper.EmployeeBankAccountMapper;
import com.nova.talentnova.model.Bank;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.EmployeeBankAccount;
import com.nova.talentnova.repository.IBankRepository;
import com.nova.talentnova.repository.IEmployeeBankAccountRepository;
import com.nova.talentnova.repository.IEmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeBankAccountServiceImpl implements IEmployeeBankAccountService {

    private final IEmployeeBankAccountRepository bankAccountRepository;
    private final IEmployeeRepository employeeRepository;
    private final IBankRepository bankRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeBankAccountResponseDto> findByEmployeeId(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EntityNotFoundException("Empleado no encontrado con ID: " + employeeId);
        }
        return bankAccountRepository.findByEmployeeIdAndStatus(employeeId, GeneralStatus.ACTIVE).stream()
                .map(EmployeeBankAccountMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // REGISTRAR CUENTA
    @Override
    @Transactional
    public EmployeeBankAccountResponseDto registerAccount(Long employeeId, EmployeeBankAccountRequestDto requestDto) {

        // BUSCAR EMPLEADO
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + employeeId));

        // BUSCAR BANCO
        Bank bank = bankRepository.findById(requestDto.getBankId())
                .orElseThrow(() -> new EntityNotFoundException("Banco no encontrado con ID: " + requestDto.getBankId()));

        // VALIDAR QUE EL NÚMERO DE CUENTA NO EXISTA
        if (bankAccountRepository.existsByAccountNumber(requestDto.getAccountNumber())) {
            throw new IllegalArgumentException("El número de cuenta ya se encuentra registrado en el sistema.");
        }

        // VALIDAR QUE EL NÚMERO CCI NO EXISTA
        if (bankAccountRepository.existsByCciNumber(requestDto.getCciNumber())) {
            throw new IllegalArgumentException("El número CCI ya se encuentra registrado en el sistema.");
        }

        EmployeeBankAccount account = EmployeeBankAccountMapper.toEntity(requestDto, employee, bank);
        EmployeeBankAccount savedAccount = bankAccountRepository.save(account);

        return EmployeeBankAccountMapper.toResponseDto(savedAccount);
    }

    // ELIMINAR CUENTA (BORRADO LÓGICO)
    @Override
    @Transactional
    public void deleteAccount(Long id) {
        EmployeeBankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta bancaria no encontrada con ID: " + id));

        account.setStatus(GeneralStatus.INACTIVE);
        bankAccountRepository.save(account);
    }
}