package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.EmployeeBankAccountRequestDto;
import com.nova.talentnova.dto.EmployeeBankAccountResponseDto;
import com.nova.talentnova.model.Bank;
import com.nova.talentnova.model.EmployeeBankAccount;

public class EmployeeBankAccountMapper {

    public static EmployeeBankAccount toEntity(EmployeeBankAccountRequestDto dto) {
        if (dto == null) {
            return null;
        }

        EmployeeBankAccount account = new EmployeeBankAccount();
        account.setAccountNumber(dto.getAccountNumber());
        account.setCciNumber(dto.getCciNumber());
        account.setAccountType(dto.getAccountType());
        account.setIsSalaryAccount(dto.getIsSalaryAccount() != null ? dto.getIsSalaryAccount() : true);
        account.setStatus(true);

        return account;
    }

    public static EmployeeBankAccountResponseDto toResponseDto(EmployeeBankAccount entity) {
        if (entity == null) {
            return null;
        }

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