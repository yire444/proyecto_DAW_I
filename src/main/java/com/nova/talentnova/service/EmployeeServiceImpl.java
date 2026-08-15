package com.nova.talentnova.service;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.dto.EmployeeFilterDto;
import com.nova.talentnova.dto.EmployeeRequestDto;
import com.nova.talentnova.dto.EmployeeResponseDto;
import com.nova.talentnova.mapper.EmployeeMapper;
import com.nova.talentnova.model.*;
import com.nova.talentnova.repository.ICompanyRepository;
import com.nova.talentnova.repository.IEmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final ICompanyRepository companyRepository;
    private final EmployeeMapper employeeMapper;

    @PersistenceContext
    private EntityManager entityManager;

    // LISTAR CON FILTROS
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> filterEmployees(EmployeeFilterDto filter) {
        Long companyId = null; // Se adaptará con JWT posteriormente

        List<Employee> employees = employeeRepository.filterEmployees(
                companyId,
                filter.getId(),
                filter.getCorporateEmail(),
                filter.getStatus(),
                filter.getWorkShiftId(),
                filter.getDepartamentId(),
                filter.getJobPositionId()
        );

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
        // VALIDAR DATOS REPETIDOS
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

        // ASIGNAR A EMPRESA
        Long currentCompanyId = 1L;
        Company company = companyRepository.findById(currentCompanyId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));
        employee.setCompany(company);

        // ASIGNAR CATÁLOGOS CON GETREFERENCE (Optimizado)
        setCatalogs(employee, requestDto);

        // GENERAR CORREO DINÁMICO USANDO EL DOMINIO REAL DE LA EMPRESA
        String corporateEmail = generateCorporateEmail(employee.getName(), employee.getLastname(), company.getEmailCompany());
        employee.setCorporateEmail(corporateEmail);

        // SE CREA ACTIVO
        employee.setStatus(GeneralStatus.ACTIVE);

        // GUARDAR
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponseDto(savedEmployee);
    }

    // ACTUALIZAR
    @Override
    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto) {
        // EMPLEADO EXISTE
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con el ID: " + id));

        // VALIDAR DATOS REPETIDOS (Excluyendo el propio ID)
        if (employeeRepository.existsByMobilePhoneAndIdNot(requestDto.getMobilePhone(), id)) {
            throw new IllegalArgumentException("El teléfono móvil ya está siendo usado por otro empleado.");
        }
        if (employeeRepository.existsByPersonalEmailAndIdNot(requestDto.getPersonalEmail(), id)) {
            throw new IllegalArgumentException("El correo personal ya está siendo usado por otro empleado.");
        }

        // ACTUALIZAR DATOS EDITABLES
        existingEmployee.setName(requestDto.getName());
        existingEmployee.setLastname(requestDto.getLastname());
        existingEmployee.setAddress(requestDto.getAddress());
        existingEmployee.setMobilePhone(requestDto.getMobilePhone());
        existingEmployee.setPersonalEmail(requestDto.getPersonalEmail());
        existingEmployee.setSalary(requestDto.getSalary());

        // ACTUALIZAR DATOS DE CATÁLOGOS USANDO GETREFERENCE
        setCatalogs(existingEmployee, requestDto);

        // GUARDAR
        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return employeeMapper.toResponseDto(updatedEmployee);
    }

    // ELIMINAR (Soft Delete)
    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + id));

        employee.setStatus(GeneralStatus.INACTIVE);
        employeeRepository.save(employee);
    }

    // ASOCIAR CATÁLOGOS CON GETREFERENCE
    private void setCatalogs(Employee employee, EmployeeRequestDto dto) {
        employee.setDocumentType(entityManager.getReference(DocumentType.class, dto.getDocumentTypeId()));
        employee.setJobPosition(entityManager.getReference(JobPosition.class, dto.getJobPositionId()));
        employee.setDepartment(entityManager.getReference(WorkArea.class, dto.getDepartamentId()));
        employee.setContractType(entityManager.getReference(ContractType.class, dto.getContractTypeId()));
        employee.setWorkShift(entityManager.getReference(WorkShift.class, dto.getWorkShiftId()));
        employee.setInsuranceScheme(entityManager.getReference(InsuranceScheme.class, dto.getInsuranceSchemeId()));
        employee.setPensionScheme(entityManager.getReference(PensionScheme.class, dto.getPensionSchemeId()));
    }

    // GENERAR CORREO CORPORATIVO DINÁMICO
    private String generateCorporateEmail(String name, String lastname, String companyEmail) {
        String cleanName = name.trim().toLowerCase()
                .replaceAll("[áàäâ]", "a").replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i").replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u").replaceAll("[^a-z]", "");

        String cleanLastname = lastname.trim().toLowerCase()
                .replaceAll("[áàäâ]", "a").replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i").replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u").replaceAll("[^a-z]", "");

        String namePrefix = cleanName.length() >= 2 ? cleanName.substring(0, 2) : cleanName;
        String firstLastname = cleanLastname.split(" ")[0];

        // Extraer dominio real de la empresa (Ej: contacto@tambo.pe -> @tambo.pe)
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