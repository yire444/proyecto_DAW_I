package com.nova.talentnova.service;

import com.nova.talentnova.dto.InsuranceSchemeRequestDTO;
import com.nova.talentnova.dto.InsuranceSchemeResponseDTO;

import java.util.List;

public interface IInsuranceSchemeService {

    //LISTAR
    List<InsuranceSchemeResponseDTO> findAll();

    //BUSCAR
    InsuranceSchemeResponseDTO findById(Long id);

    //REGISTRAR
    InsuranceSchemeResponseDTO register(InsuranceSchemeRequestDTO dto);

    //ACTUALIZAR
    InsuranceSchemeResponseDTO update(Long id, InsuranceSchemeRequestDTO dto);

    //ELIIMINAR
    void delete(Long id);
}