package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.EmployeeBankAccountRequestDto;
import com.nova.talentnova.dto.EmployeeBankAccountResponseDto;
import com.nova.talentnova.model.Bank;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.model.EmployeeBankAccount;

public class EmployeeBankAccountMapper {

    //DTO -> ENTIDAD
    public static EmployeeBankAccount toEntity(EmployeeBankAccountRequestDto dto, Employee employee, Bank bank) {

        if (dto == null) {return null;}

        EmployeeBankAccount account = new EmployeeBankAccount();
        account.setEmployee(employee);
        account.setBank(bank);
        account.setAccountNumber(dto.getAccountNumber());
        account.setCciNumber(dto.getCciNumber());
        account.setAccountType(dto.getAccountType());
        account.setIsSalaryAccount(dto.getIsSalaryAccount() != null ? dto.getIsSalaryAccount() : true);

        return account;
    }

    //ENTIDAD -> DTO
    public static EmployeeBankAccountResponseDto toResponseDto(EmployeeBankAccount entity) {

        if (entity == null) {return null;}

        EmployeeBankAccountResponseDto dto = new EmployeeBankAccountResponseDto();
        dto.setId(entity.getId());

        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
            dto.setEmployeeFullName(entity.getEmployee().getName() + " " + entity.getEmployee().getLastname());
        }

        if (entity.getBank() != null) {
            dto.setBankId(entity.getBank().getId());
            dto.setBankName(entity.getBank().getName());
        }

        dto.setAccountNumber(entity.getAccountNumber());
        dto.setCciNumber(entity.getCciNumber());
        dto.setAccountType(entity.getAccountType());
        dto.setIsSalaryAccount(entity.getIsSalaryAccount());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }
}