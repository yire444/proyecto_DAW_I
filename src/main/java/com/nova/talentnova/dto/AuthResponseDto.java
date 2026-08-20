package com.nova.talentnova.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private String token;
    private String type = "Bearer";
    private String email;
    private String systemRole;
    private Long id;
}