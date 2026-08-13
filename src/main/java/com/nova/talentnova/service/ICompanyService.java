package com.nova.talentnova.service;

import com.nova.talentnova.dto.CompanyFilterDto;
import com.nova.talentnova.dto.CompanyRequestDto;
import com.nova.talentnova.dto.CompanyResponseDto;
import com.nova.talentnova.dto.CompanyUpdateDto;

import java.util.List;

public interface ICompanyService {

    //C: REGISTRAR EMPRESA
    CompanyResponseDto createCompany(CompanyRequestDto dto);

    //R: ACTIVAR EMPRESA POR CORREO Y CÓDIGO
    void activateCompany(String email, String code);

    //R: LOGIN
    String loginCompany(String email, String password);

    //R: LISTAR Y FILTRAR
    List<CompanyResponseDto> filterCompanies(CompanyFilterDto dto);

    //R: BUSCAR POR ID
    CompanyResponseDto findById(Long id);

    //U: ACTUALIZAR DATOS
    CompanyResponseDto updateCompany(Long id, CompanyUpdateDto dto);

    //D: ELIMINAR (CAMBIO DE ESTADO A INACTIVE)
    void deleteCompany(Long id);
}