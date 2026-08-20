package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.EmployeeProfileUpdateDto;
import com.nova.talentnova.dto.EmployeeRequestDto;
import com.nova.talentnova.dto.EmployeeResponseDto;
import com.nova.talentnova.dto.EmployeeUpdateDto;
import com.nova.talentnova.model.*;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDto dto) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setLastname(dto.getLastname());
        employee.setBirthdate(dto.getBirthdate());
        employee.setGender(dto.getGender());
        employee.setAddress(dto.getAddress());
        employee.setMobilePhone(dto.getMobilePhone());
        employee.setPersonalEmail(dto.getPersonalEmail());
        employee.setDocumentNumber(dto.getDocumentNumber());
        employee.setStartDate(dto.getStartDate());
        employee.setSalary(dto.getSalary());

        if (dto.getDocumentTypeId() != null) {
            DocumentType docType = new DocumentType();
            docType.setId(dto.getDocumentTypeId());
            employee.setDocumentType(docType);
        }
        if (dto.getJobPositionId() != null) {
            JobPosition jobPos = new JobPosition();
            jobPos.setId(dto.getJobPositionId());
            employee.setJobPosition(jobPos);
        }
        if (dto.getDepartmentId() != null) {
            WorkArea dept = new WorkArea();
            dept.setId(dto.getDepartmentId());
            employee.setDepartment(dept);
        }
        if (dto.getContractTypeId() != null) {
            ContractType contract = new ContractType();
            contract.setId(dto.getContractTypeId());
            employee.setContractType(contract);
        }
        if (dto.getWorkShiftId() != null) {
            WorkShift shift = new WorkShift();
            shift.setId(dto.getWorkShiftId());
            employee.setWorkShift(shift);
        }
        if (dto.getInsuranceSchemeId() != null) {
            InsuranceScheme insurance = new InsuranceScheme();
            insurance.setId(dto.getInsuranceSchemeId());
            employee.setInsuranceScheme(insurance);
        }
        if (dto.getPensionSchemeId() != null) {
            PensionScheme pension = new PensionScheme();
            pension.setId(dto.getPensionSchemeId());
            employee.setPensionScheme(pension);
        }

        return employee;
    }

    public EmployeeResponseDto toResponseDto(Employee entity) {
        if (entity == null) return null;

        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(entity.getId());
        if (entity.getCompany() != null) {
            dto.setCompanyId(entity.getCompany().getId());
        }
        dto.setName(entity.getName());
        dto.setLastname(entity.getLastname());
        dto.setBirthdate(entity.getBirthdate());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setMobilePhone(entity.getMobilePhone());
        dto.setPersonalEmail(entity.getPersonalEmail());
        dto.setCorporateEmail(entity.getCorporateEmail());
        dto.setDocumentNumber(entity.getDocumentNumber());
        dto.setStartDate(entity.getStartDate());
        dto.setSalary(entity.getSalary());
        dto.setStatus(entity.getStatus());
        dto.setSystemRole(entity.getSystemRole());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getDocumentType() != null) {
            dto.setDocumentTypeName(entity.getDocumentType().getName());
        }
        if (entity.getJobPosition() != null) {
            dto.setJobPositionName(entity.getJobPosition().getName());
        }
        if (entity.getDepartment() != null) {
            dto.setDepartmentName(entity.getDepartment().getName());
        }
        if (entity.getContractType() != null) {
            dto.setContractTypeName(entity.getContractType().getName());
        }
        if (entity.getWorkShift() != null) {
            dto.setWorkShiftName(entity.getWorkShift().getName());
        }
        if (entity.getInsuranceScheme() != null) {
            dto.setInsuranceSchemeName(entity.getInsuranceScheme().getName());
        }
        if (entity.getPensionScheme() != null) {
            dto.setPensionSchemeName(entity.getPensionScheme().getName());
        }

        return dto;
    }

    public void updateEntityFromDto(EmployeeUpdateDto dto, Employee employee) {
        if (dto == null || employee == null) return;

        employee.setAddress(dto.getAddress());
        employee.setMobilePhone(dto.getMobilePhone());
        employee.setPersonalEmail(dto.getPersonalEmail());
        employee.setSalary(dto.getSalary());

        if (dto.getJobPositionId() != null) {
            JobPosition jobPos = new JobPosition();
            jobPos.setId(dto.getJobPositionId());
            employee.setJobPosition(jobPos);
        }
        if (dto.getDepartmentId() != null) {
            WorkArea dept = new WorkArea();
            dept.setId(dto.getDepartmentId());
            employee.setDepartment(dept);
        }
        if (dto.getContractTypeId() != null) {
            ContractType contract = new ContractType();
            contract.setId(dto.getContractTypeId());
            employee.setContractType(contract);
        }
        if (dto.getWorkShiftId() != null) {
            WorkShift shift = new WorkShift();
            shift.setId(dto.getWorkShiftId());
            employee.setWorkShift(shift);
        }
        if (dto.getInsuranceSchemeId() != null) {
            InsuranceScheme insurance = new InsuranceScheme();
            insurance.setId(dto.getInsuranceSchemeId());
            employee.setInsuranceScheme(insurance);
        }
        if (dto.getPensionSchemeId() != null) {
            PensionScheme pension = new PensionScheme();
            pension.setId(dto.getPensionSchemeId());
            employee.setPensionScheme(pension);
        }
    }

    public void updateProfileFromDto(EmployeeProfileUpdateDto dto, Employee employee) {
        if (dto == null || employee == null) return;

        employee.setAddress(dto.getAddress());
        employee.setMobilePhone(dto.getMobilePhone());
        employee.setPersonalEmail(dto.getPersonalEmail());
    }
}