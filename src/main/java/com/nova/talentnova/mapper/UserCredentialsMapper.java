package com.nova.talentnova.mapper;

import com.nova.talentnova.dto.AuthResponseDto;
import com.nova.talentnova.model.UserCredentials;

public class UserCredentialsMapper {

    public static AuthResponseDto toAuthResponseDto(UserCredentials credentials, String token) {
        if (credentials == null) {return null;}

        AuthResponseDto dto = new AuthResponseDto();
        dto.setToken(token);
        dto.setType("Bearer");
        
        if (credentials.getEmployee() != null) {
            dto.setCorporateEmail(credentials.getEmployee().getCorporateEmail());
            dto.setEmployeeId(credentials.getEmployee().getId());
        }
        
        dto.setSystemRole(credentials.getSystemRole());

        return dto;
    }
}