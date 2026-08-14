package com.nova.talentnova.service;

import com.nova.talentnova.GeneralStatus;
import com.nova.talentnova.dto.CompanyFilterDto;
import com.nova.talentnova.dto.CompanyRequestDto;
import com.nova.talentnova.dto.CompanyResponseDto;
import com.nova.talentnova.dto.CompanyUpdateDto;
import com.nova.talentnova.mapper.CompanyMapper;
import com.nova.talentnova.model.BillingCycle;
import com.nova.talentnova.model.CodeGenerator;
import com.nova.talentnova.model.Company;
import com.nova.talentnova.model.DocumentType;
import com.nova.talentnova.model.PlanType;
import com.nova.talentnova.repository.IBillingCycleRepository;
import com.nova.talentnova.repository.ICompanyRepository;
import com.nova.talentnova.repository.IDocumentTypeRepository;
import com.nova.talentnova.repository.IPlanTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final IPlanTypeRepository planTypeRepository;
    private final IBillingCycleRepository billingCycleRepository;
    private final IDocumentTypeRepository documentTypeRepository;
    private final CompanyMapper companyMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    //R: REGISTRAR EMPRESA
    @Override
    @Transactional
    public CompanyResponseDto createCompany(CompanyRequestDto dto) {

        //VALIDAR DATOS REPETIDOS
        if (companyRepository.findByRuc(dto.getRuc()).isPresent()) {
            throw new RuntimeException("Ya existe una empresa registrada con este RUC");
        }
        if (companyRepository.findByEmailCompany(dto.getEmailCompany()).isPresent()) {
            throw new RuntimeException("Ya existe una empresa registrada con este correo");
        }
        if (companyRepository.findByPhoneCompany(dto.getPhoneCompany()).isPresent()) {
            throw new RuntimeException("Ya existe una empresa registrada con este teléfono");
        }

        //SELECCIONAR LOS TIPOS DE PLAN, CICLOS DE FACTURACIÓN Y TIPO DE DOCUMENTO
        PlanType planType = planTypeRepository.findById(dto.getPlanTypeId())
                .orElseThrow(() -> new RuntimeException("El tipo de plan seleccionado no existe"));

        BillingCycle billingCycle = billingCycleRepository.findById(dto.getBillingCycleId())
                .orElseThrow(() -> new RuntimeException("El ciclo de facturación seleccionado no existe"));

        DocumentType documentType = documentTypeRepository.findById(dto.getDocumentTypeId().intValue())
                .orElseThrow(() -> new RuntimeException("El tipo de documento seleccionado no existe"));

        String verificationCode = CodeGenerator.generateVerificationCode();

        //MAPPER
        Company company = companyMapper.toEntity(dto);

        //ENCRIPTAR CONTRASEÑA, ASIGNAR RELACIONES, FECHA Y CÓDIGO
        company.setPassword(passwordEncoder.encode(dto.getPassword()));
        company.setPlanType(planType);
        company.setBillingCycle(billingCycle);
        company.setDocumentType(documentType);
        company.setCreatedDate(LocalDate.now());
        company.setVerificationCode(verificationCode);

        //GUARDAR
        Company savedCompany = companyRepository.save(company);

        //ENVIAR CORREO
        emailService.sendActivationEmail(
                savedCompany.getEmailCompany(),
                savedCompany.getNameCompany(),
                verificationCode
        );

        return companyMapper.toResponseDto(savedCompany);
    }

    //ACTIVAR CUENTA
    @Override
    @Transactional
    public void activateCompany(String email, String code) {
        Company company = companyRepository.findByEmailCompanyAndVerificationCode(email, code)
                .orElseThrow(() -> new RuntimeException("Código incorrecto o empresa no encontrada"));

        if (company.getStatus() == GeneralStatus.ACTIVE) {
            throw new RuntimeException("Esta cuenta ya está activada");
        }

        company.setStatus(GeneralStatus.ACTIVE);
        company.setVerificationCode(null);
        companyRepository.save(company);
    }

    // LOGIN DE EMPRESA
    @Override
    public String loginCompany(String email, String password) {
        Company company = companyRepository.findByEmailCompany(email)
                .orElseThrow(() -> new RuntimeException("Correo o contraseña incorrectos"));

        if (company.getStatus() != GeneralStatus.ACTIVE) {
            throw new RuntimeException("Tu cuenta aún no está activada. Por favor, ingresa el código de verificación.");
        }

        if (!passwordEncoder.matches(password, company.getPassword())) {
            throw new RuntimeException("Correo o contraseña incorrectos");
        }

        return "¡Login exitoso! Bienvenido " + company.getNameCompany();
    }

    //LISTAR Y FILTRAR
    @Override
    @Transactional
    public List<CompanyResponseDto> filterCompanies(CompanyFilterDto dto) {
        List<Company> companies = companyRepository.filterByCompany(
                dto.getId(),
                dto.getRuc(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getStatus(),
                dto.getPlanTypeId(),
                dto.getBillingCycleId()
        );

        return companies.stream()
                .map(companyMapper::toResponseDto)
                .toList();
    }

    //BUSCAR POR ID
    @Override
    @Transactional()
    public CompanyResponseDto findById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con el ID: " + id));
        return companyMapper.toResponseDto(company);
    }

    //ACTUALIZAR EMPRESA
    @Override
    @Transactional
    public CompanyResponseDto updateCompany(Long id, CompanyUpdateDto dto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con el ID: " + id));

        if (dto.getEmailCompany() != null && !dto.getEmailCompany().isBlank()) {
            if (!dto.getEmailCompany().equals(company.getEmailCompany())) {
                if (companyRepository.findByEmailCompanyAndIdNot(dto.getEmailCompany(), id).isPresent()) {
                    throw new RuntimeException("El correo ya se encuentra registrado");
                }
                company.setEmailCompany(dto.getEmailCompany());
            }
        }

        if (dto.getPhoneCompany() != null && !dto.getPhoneCompany().isBlank()) {
            if (!dto.getPhoneCompany().equals(company.getPhoneCompany())) {
                if (companyRepository.findByPhoneCompanyAndIdNot(dto.getPhoneCompany(), id).isPresent()) {
                    throw new RuntimeException("El teléfono ya se encuentra registrado");
                }
                company.setPhoneCompany(dto.getPhoneCompany());
            }
        }

        if (dto.getPlanTypeId() != null) {
            PlanType planType = planTypeRepository.findById(dto.getPlanTypeId())
                    .orElseThrow(() -> new RuntimeException("El tipo de plan seleccionado no existe"));
            company.setPlanType(planType);
        }

        if (dto.getBillingCycleId() != null) {
            BillingCycle billingCycle = billingCycleRepository.findById(dto.getBillingCycleId())
                    .orElseThrow(() -> new RuntimeException("El ciclo de facturación seleccionado no existe"));
            company.setBillingCycle(billingCycle);
        }

        Company updatedCompany = companyRepository.save(company);
        return companyMapper.toResponseDto(updatedCompany);
    }

    //ELIMINAR
    @Override
    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con el ID: " + id));

        company.setStatus(GeneralStatus.INACTIVE);
        companyRepository.save(company);
    }
}