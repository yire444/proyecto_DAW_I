package com.nova.talentnova.service;

import com.nova.talentnova.dto.AuthResponseDto;
import com.nova.talentnova.dto.LoginRequestDto;

public interface IAuthService {
    AuthResponseDto login(LoginRequestDto request);
}