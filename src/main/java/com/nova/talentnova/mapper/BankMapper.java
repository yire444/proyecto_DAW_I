package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.BankRequestDTO;
import com.nova.talentnova.dto.BankResponseDTO;
import com.nova.talentnova.model.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankMapper {

    public Bank toEntity(BankRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Bank entity = new Bank();
        entity.setName(dto.getName());
        return entity;
    }

    public BankResponseDTO toResponseDTO(Bank entity) {
        if (entity == null) {
            return null;
        }
        BankResponseDTO dto = new BankResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}