package com.nova.talentnova.dto;

import com.nova.talentnova.GeneralStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDto {

    private Long id;
    private String nameCompany;
    private String ruc;
    private String nameHolder;
    private String lastNameHolder;
    private String documentNumber;
    private String emailCompany;
    private String phoneCompany;
    private LocalDate createdDate;
    private GeneralStatus status;

    private String documentTypeName;
    private String planName;
    private String billingCycleName;
}