package com.nova.talentnova.service;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.SystemRole;
import com.nova.talentnova.dto.*;
import com.nova.talentnova.mapper.EmployeeMapper;
import com.nova.talentnova.model.*;
import com.nova.talentnova.repository.*;
import com.nova.talentnova.security.JwtUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final ICompanyRepository companyRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final HttpServletRequest request;
    private final JwtUtils jwtUtils;

    @PersistenceContext
    private EntityManager entityManager;

    // LISTAR CON FILTROS
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> filterEmployees(EmployeeFilterDto filter) {
        String authHeader = request.getHeader("Authorization");
        Long companyId = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            companyId = jwtUtils.getCompanyIdFromToken(token);
        }

        List<Employee> employees;

        if (filter == null || (filter.getId() == null && filter.getCorporateEmail() == null &&
                filter.getStatus() == null && filter.getWorkShiftId() == null &&
                filter.getDepartmentId() == null && filter.getJobPositionId() == null)) {

            employees = employeeRepository.findByCompanyId(companyId);
        } else {
            employees = employeeRepository.filterEmployees(
                    companyId,
                    filter.getId(),
                    filter.getCorporateEmail(),
                    filter.getStatus(),
                    filter.getWorkShiftId(),
                    filter.getDepartmentId(),
                    filter.getJobPositionId()
            );
        }

        return employees.stream()
                .map(employeeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDto findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con el ID: " + id));
        return employeeMapper.toResponseDto(employee);
    }

    // REGISTRAR
    @Override
    @Transactional
    public EmployeeResponseDto registerEmployee(EmployeeRequestDto requestDto) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("No se encontró el token de autorización en la petición.");
        }
        String token = authHeader.substring(7);
        Long companyId = jwtUtils.getCompanyIdFromToken(token);

        if (employeeRepository.existsByMobilePhone(requestDto.getMobilePhone())) {
            throw new IllegalArgumentException("Ya existe un empleado registrado con el teléfono móvil: " + requestDto.getMobilePhone());
        }
        if (employeeRepository.existsByPersonalEmail(requestDto.getPersonalEmail())) {
            throw new IllegalArgumentException("Ya existe un empleado registrado con el correo personal: " + requestDto.getPersonalEmail());
        }
        if (employeeRepository.existsByDocumentNumber(requestDto.getDocumentNumber())) {
            throw new IllegalArgumentException("Ya existe un empleado registrado con el número de documento: " + requestDto.getDocumentNumber());
        }

        Employee employee = employeeMapper.toEntity(requestDto);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + companyId));
        employee.setCompany(company);

        setCatalogs(employee, requestDto);

        String corporateEmail = generateCorporateEmail(employee.getName(), employee.getLastname(), company.getEmailCompany());
        employee.setCorporateEmail(corporateEmail);

        String tempPassword = UUID.randomUUID().toString().substring(0, 8);
        employee.setPasswordHash(passwordEncoder.encode(tempPassword));
        String shortToken = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        employee.setActivationToken(shortToken);

        employee.setStatus(GeneralStatus.PENDING);
        employee.setSystemRole(SystemRole.EMPLOYEE);

        Employee savedEmployee = employeeRepository.save(employee);

        emailService.sendEmployeeWelcomeEmail(
                savedEmployee.getPersonalEmail(),
                savedEmployee.getName(),
                savedEmployee.getCorporateEmail(),
                tempPassword,
                savedEmployee.getActivationToken()
        );

        return employeeMapper.toResponseDto(savedEmployee);
    }

    // 1. ACTIVAR CUENTA
    @Override
    @Transactional
    public void activateEmployee(String corporateEmail, String token) {
        Employee employee = employeeRepository.findByCorporateEmail(corporateEmail)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (employee.getActivationToken() == null || !employee.getActivationToken().equals(token)) {
            throw new RuntimeException("Token de activación inválido o expirado");
        }

        employee.setStatus(GeneralStatus.ACTIVE);
        employee.setActivationToken(null);

        employeeRepository.save(employee);
    }

    // LOGIN DEL EMPLEADO
    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDto loginEmployee(String corporateEmail, String password) {
        Employee employee = employeeRepository.findByCorporateEmail(corporateEmail)
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(password, employee.getPasswordHash())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        if (employee.getStatus() != GeneralStatus.ACTIVE) {
            throw new RuntimeException("La cuenta no está activa. Por favor, actívala primero.");
        }

        return employeeMapper.toResponseDto(employee);
    }

    // 2. ACTUALIZAR PERFIL (Propio del Empleado)
    @Override
    @Transactional
    public EmployeeResponseDto updateEmployeeProfile(Long id, EmployeeProfileUpdateDto dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        employee.setAddress(dto.getAddress());
        employee.setMobilePhone(dto.getMobilePhone());
        employee.setPersonalEmail(dto.getPersonalEmail());

        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            employee.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
        }

        return employeeMapper.toResponseDto(employeeRepository.save(employee));
    }

    // 3. ACTUALIZAR DE ADMIN EMPRESA
    @Override
    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, EmployeeUpdateDto dto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con el ID: " + id));

        if (employeeRepository.existsByMobilePhoneAndIdNot(dto.getMobilePhone(), id)) {
            throw new IllegalArgumentException("El teléfono móvil ya está siendo usado por otro empleado.");
        }
        if (employeeRepository.existsByPersonalEmailAndIdNot(dto.getPersonalEmail(), id)) {
            throw new IllegalArgumentException("El correo personal ya está siendo usado por otro empleado.");
        }

        employeeMapper.updateEntityFromDto(dto, existingEmployee);

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return employeeMapper.toResponseDto(updatedEmployee);
    }

    // ELIMINAR (ELIMINACIÓN LÓGICA)
    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + id));

        employee.setStatus(GeneralStatus.INACTIVE);
        employeeRepository.save(employee);
    }

    //OBTENER CATÁLOGOS
    private void setCatalogs(Employee employee, EmployeeRequestDto dto) {
        employee.setDocumentType(entityManager.getReference(DocumentType.class, dto.getDocumentTypeId()));
        employee.setJobPosition(entityManager.getReference(JobPosition.class, dto.getJobPositionId()));
        employee.setDepartment(entityManager.getReference(WorkArea.class, dto.getDepartmentId()));
        employee.setContractType(entityManager.getReference(ContractType.class, dto.getContractTypeId()));
        employee.setWorkShift(entityManager.getReference(WorkShift.class, dto.getWorkShiftId()));
        employee.setInsuranceScheme(entityManager.getReference(InsuranceScheme.class, dto.getInsuranceSchemeId()));
        employee.setPensionScheme(entityManager.getReference(PensionScheme.class, dto.getPensionSchemeId()));
    }

    // GENERAR CORREO CORPORATIVO DINÁMICO
    private String generateCorporateEmail(String name, String lastname, String companyEmail) {
        //LIMPIAR LAS TILDES DEL NOMBRE Y APELLIDO
        String cleanName = name.trim().toLowerCase()
                .replaceAll("[áàäâ]", "a").replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i").replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u").replaceAll("[^a-z]", "");

        String cleanLastname = lastname.trim().toLowerCase()
                .replaceAll("[áàäâ]", "a").replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i").replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u").replaceAll("[^a-z]", "");

        //OBETNER LAS 2 PRIMERAS LETRAS DEL NOMBRE LIMPIO
        String namePrefix = cleanName.length() >= 2 ? cleanName.substring(0, 2) : cleanName;
        String firstLastname = cleanLastname.split(" ")[0];

        String domain = companyEmail.contains("@") ? companyEmail.substring(companyEmail.indexOf("@")) : "@talentnova.com";

        String baseEmail = namePrefix + firstLastname + domain;
        String finalEmail = baseEmail;
        int counter = 1;

        while (employeeRepository.findByCorporateEmail(finalEmail).isPresent()) {
            finalEmail = namePrefix + firstLastname + counter + domain;
            counter++;
        }

        return finalEmail;
    }
}