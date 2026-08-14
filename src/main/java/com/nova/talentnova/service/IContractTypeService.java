package com.nova.talentnova.service;

import com.nova.talentnova.dto.ContractTypeRequestDTO;
import com.nova.talentnova.dto.ContractTypeResponseDTO;

import java.util.List;

public interface IContractTypeService {

    //LISTAR
    List<ContractTypeResponseDTO> findAll();

    //BUSCAR POR ID
    ContractTypeResponseDTO findById(Long id);

    //REGISTRAR
    ContractTypeResponseDTO register(ContractTypeRequestDTO dto);

    //ACTUALIZAR
    ContractTypeResponseDTO update(Long id, ContractTypeRequestDTO dto);

    //ELIMINAR
    void delete(Long id);
}