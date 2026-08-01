package com.nova.talentnova.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EmployeeBankAccountResponseDto {

    private Integer id;
    private Integer employeeId;
    private String employeeFullName;
    private Integer bankId;
    private String bankName;
    private String accountNumber;
    private String cciNumber;
    private String accountType;
    private Boolean isSalaryAccount;
    private Boolean status;
    private LocalDateTime createdAt;
}