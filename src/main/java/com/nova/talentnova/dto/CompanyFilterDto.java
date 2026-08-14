package com.nova.talentnova.dto;

import com.nova.talentnova.GeneralStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyFilterDto {
    private Long id;
    private String ruc;
    private String name;
    private String email;
    private String phone;
    private GeneralStatus status;
    private Long planTypeId;
    private Long billingCycleId;
}
