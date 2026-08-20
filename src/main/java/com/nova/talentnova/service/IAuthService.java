package com.nova.talentnova.service;

import com.nova.talentnova.dto.AuthResponseDto;
import com.nova.talentnova.dto.LoginRequestDto;

public interface IAuthService {
    AuthResponseDto loginEmployee(LoginRequestDto request);
    AuthResponseDto loginCompany(LoginRequestDto request);
}