package com.nova.talentnova.service;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.dto.AuthResponseDto;
import com.nova.talentnova.dto.LoginRequestDto;
import com.nova.talentnova.model.Company;
import com.nova.talentnova.model.Employee;
import com.nova.talentnova.repository.ICompanyRepository;
import com.nova.talentnova.repository.IEmployeeRepository;
import com.nova.talentnova.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IEmployeeRepository employeeRepository;
    private final ICompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    // LOGIN PARA EMPLEADOS
    @Override
    public AuthResponseDto loginEmployee(LoginRequestDto request) {

        Employee employee = employeeRepository.findByCorporateEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (employee.getStatus() != GeneralStatus.ACTIVE) {
            throw new RuntimeException("La cuenta del empleado aún no ha sido activada o se encuentra pendiente/suspendida");
        }

        if (!passwordEncoder.matches(request.getPassword(), employee.getPasswordHash())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        Long companyId = employee.getCompany().getId();
        String token = jwtUtils.generateToken(employee.getCorporateEmail(), companyId);

        return new AuthResponseDto(
                token,
                "Bearer",
                employee.getCorporateEmail(),
                employee.getSystemRole().name(),
                employee.getId()
        );
    }

    // LOGIN PARA EMPRESAS (ADMINISTRADORES)
    @Override
    public AuthResponseDto loginCompany(LoginRequestDto request) {

        Company company = companyRepository.findByEmailCompany(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (company.getStatus() == GeneralStatus.PENDING) {
            throw new RuntimeException("La empresa aún no ha verificado su cuenta");
        }

        if (!passwordEncoder.matches(request.getPassword(), company.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtUtils.generateToken(company.getEmailCompany(), company.getId());

        return new AuthResponseDto(
                token,
                "Bearer",
                company.getEmailCompany(),
                "COMPANY",
                company.getId()
        );
    }
}