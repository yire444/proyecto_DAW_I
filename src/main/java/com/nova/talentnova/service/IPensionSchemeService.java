package com.nova.talentnova.service;

import com.nova.talentnova.dto.PensionSchemeRequestDTO;
import com.nova.talentnova.dto.PensionSchemeResponseDTO;

import java.util.List;

public interface IPensionSchemeService {

    //LISTAR
    List<PensionSchemeResponseDTO> findAll();

    //BUSCAR POR ID
    PensionSchemeResponseDTO findById(Long id);

    //REGISTRAR
    PensionSchemeResponseDTO register(PensionSchemeRequestDTO dto);

    //ACTUALIZAR
    PensionSchemeResponseDTO update(Long id, PensionSchemeRequestDTO dto);

    //ELIMINAR
    void delete(Long id);
}