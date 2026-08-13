package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.CompanyRequestDto;
import com.nova.talentnova.dto.CompanyResponseDto;
import com.nova.talentnova.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Company company = new Company();
        company.setNameCompany(dto.getNameCompany());
        company.setRuc(dto.getRuc());
        company.setNameHolder(dto.getNameHolder());
        company.setLastNameHolder(dto.getLastNameHolder());
        company.setDocumentNumber(dto.getDocumentNumber());
        company.setEmailCompany(dto.getEmailCompany());
        company.setPhoneCompany(dto.getPhoneCompany());
        company.setPassword(dto.getPassword());

        return company;
    }

    public CompanyResponseDto toResponseDto(Company company) {
        if (company == null) {
            return null;
        }

        CompanyResponseDto dto = new CompanyResponseDto();
        dto.setId(company.getId());
        dto.setNameCompany(company.getNameCompany());
        dto.setRuc(company.getRuc());
        dto.setNameHolder(company.getNameHolder());
        dto.setLastNameHolder(company.getLastNameHolder());
        dto.setDocumentNumber(company.getDocumentNumber());
        dto.setEmailCompany(company.getEmailCompany());
        dto.setPhoneCompany(company.getPhoneCompany());
        dto.setCreatedDate(company.getCreatedDate());
        dto.setStatus(company.getStatus());

        if (company.getDocumentType() != null) {
            dto.setDocumentTypeName(company.getDocumentType().getName());
        }
        if (company.getPlanType() != null) {
            dto.setPlanName(company.getPlanType().getName());
        }
        if (company.getBillingCycle() != null) {
            dto.setBillingCycleName(company.getBillingCycle().getName());
        }

        return dto;
    }
}
