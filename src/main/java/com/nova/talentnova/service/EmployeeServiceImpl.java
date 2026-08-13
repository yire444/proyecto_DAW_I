package com.nova.talentnova.service;

import com.nova.talentnova.dto.EmployeeFilterDto;
import com.nova.talentnova.dto.EmployeeRequestDto;
import com.nova.talentnova.dto.EmployeeResponseDto;
import com.nova.talentnova.mapper.EmployeeMapper;
import com.nova.talentnova.model.*;
import com.nova.talentnova.repository.IEmployeeRepository;
import jakarta.persistence.EntityManager;
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

    @PersistenceContext
    private EntityManager entityManager;

    // LISTAR CON FILTROS
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> filterEmployees(EmployeeFilterDto filter) {
        if (filter == null) {
            filter = new EmployeeFilterDto();
        }

        List<Employee> employees = employeeRepository.filterEmployees(
                filter.getId(),
                filter.getCorporateEmail(),
                filter.getStatus(),
                filter.getWorkShiftId(),
                filter.getDepartamentId(),
                filter.getJobPositionId()
        );

        return employees.stream()
                .map(EmployeeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDto findById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con el ID: " + id));
        return EmployeeMapper.toResponseDto(employee);
    }

    // REGISTRAR
    @Override
    @Transactional
    public EmployeeResponseDto registerEmployee(EmployeeRequestDto requestDto) {
        // Validaciones exclusivas para el registro de un nuevo empleado
        if (employeeRepository.existsByMobilePhone(requestDto.getMobilePhone())) {
            throw new RuntimeException("El número de teléfono móvil ya se encuentra registrado.");
        }
        if (employeeRepository.existsByPersonalEmail(requestDto.getPersonalEmail())) {
            throw new RuntimeException("El correo personal ya se encuentra registrado.");
        }
        if (employeeRepository.existsByDocumentNumber(requestDto.getDocumentNumber())) {
            throw new RuntimeException("El número de documento ya se encuentra registrado.");
        }

        Employee employee = EmployeeMapper.toEntity(requestDto);
        setCatalogs(employee, requestDto);

        String corporateEmail = generateCorporateEmail(requestDto.getName(), requestDto.getLastname());
        employee.setCorporateEmail(corporateEmail);

        Employee saved = employeeRepository.save(employee);
        return EmployeeMapper.toResponseDto(saved);
    }

    // ACTUALIZAR
    @Override
    @Transactional
    public EmployeeResponseDto updateEmployee(Integer id, EmployeeRequestDto requestDto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con el ID: " + id));

        existingEmployee.setAddress(requestDto.getAddress());
        existingEmployee.setMobilePhone(requestDto.getMobilePhone());
        existingEmployee.setPersonalEmail(requestDto.getPersonalEmail());
        existingEmployee.setStartDate(requestDto.getStartDate());
        existingEmployee.setSalary(requestDto.getSalary());

        setCatalogs(existingEmployee, requestDto);

        Employee updated = employeeRepository.save(existingEmployee);
        return EmployeeMapper.toResponseDto(updated);
    }

    // ELIMINAR
    @Override
    @Transactional
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con el ID: " + id));

        employee.setStatus(false);
        employeeRepository.save(employee);
    }

    // ASOCIAR CATÁLOGOS CON GETREFERENCE
    private void setCatalogs(Employee employee, EmployeeRequestDto dto) {
        employee.setDocumentType(entityManager.getReference(DocumentType.class, dto.getDocumentTypeId()));
        employee.setJobPosition(entityManager.getReference(JobPosition.class, dto.getJobPositionId()));
        employee.setDepartament(entityManager.getReference(WorkArea.class, dto.getDepartamentId()));
        employee.setContractType(entityManager.getReference(ContractType.class, dto.getContractTypeId()));
        employee.setWorkShift(entityManager.getReference(WorkShift.class, dto.getWorkShiftId()));
        employee.setInsuranceScheme(entityManager.getReference(InsuranceScheme.class, dto.getInsuranceSchemeId()));
        employee.setPensionScheme(entityManager.getReference(PensionScheme.class, dto.getPensionSchemeId()));
    }

    //GENERAR CORREO CORPORATIVO
    private String generateCorporateEmail(String name, String lastname) {
        //LIMPIAR LETRAS
        String cleanName = name.trim().toLowerCase()
                .replaceAll("[áàäâ]", "a").replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i").replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u").replaceAll("[^a-z]", "");

        String cleanLastname = lastname.trim().toLowerCase()
                .replaceAll("[áàäâ]", "a").replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i").replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u").replaceAll("[^a-z]", "");

        //2 LETRAS DEL NOMBRE
        String namePrefix = cleanName.length() >= 2 ? cleanName.substring(0, 2) : cleanName;

        //PRIMER APELLIDO
        String firstLastname = cleanLastname.split(" ")[0];

        return namePrefix + firstLastname + "@talentnova.com";
    }
}