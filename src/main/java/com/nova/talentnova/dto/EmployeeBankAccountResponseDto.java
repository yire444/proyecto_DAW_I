package com.nova.talentnova.dto;

import com.nova.talentnova.GeneralStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EmployeeBankAccountResponseDto {
    private Long id;
    private Long employeeId;
    private String employeeFullName;
    private Long bankId;
    private String bankName;
    private String accountNumber;
    private String cciNumber;
    private String accountType;
    private Boolean isSalaryAccount;
    private GeneralStatus status;
    private LocalDateTime createdAt;
}