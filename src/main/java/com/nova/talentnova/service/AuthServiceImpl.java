package com.nova.talentnova.service;

import com.nova.talentnova.dto.AuthResponseDto;
import com.nova.talentnova.dto.LoginRequestDto;
import com.nova.talentnova.mapper.UserCredentialsMapper;
import com.nova.talentnova.model.UserCredentials;
import com.nova.talentnova.repository.IUserCredentialsRepository;
import com.nova.talentnova.security.JwtUtils;
import com.nova.talentnova.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        
        UserCredentials credentials = userCredentialsRepository.findByEmployee_CorporateEmail(request.getCorporateEmail())
                .orElseThrow(() -> new RuntimeException("Correo corporativo o contraseña incorrectos"));

        if (!credentials.getIsActivated()) {
            throw new RuntimeException("El usuario se encuentra desactivado");
        }

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), credentials.getPassword());
        
        if (!passwordMatches) {
            throw new RuntimeException("Correo corporativo o contraseña incorrectos");
        }

        String token = jwtUtils.generateToken(credentials.getEmployee().getCorporateEmail());

        return UserCredentialsMapper.toAuthResponseDto(credentials, token);
    }
}