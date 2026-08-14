package com.nova.talentnova.service;

import com.nova.talentnova.dto.BankRequestDTO;
import com.nova.talentnova.dto.BankResponseDTO;

import java.util.List;

public interface IBankService {

    //LISTAR
    List<BankResponseDTO> findAll();

    //BUSCAR POR ID
    BankResponseDTO findById(Long id);

    //REGISTRAR
    BankResponseDTO register(BankRequestDTO dto);

    //ACTUALIZAR
    BankResponseDTO update(Long id, BankRequestDTO dto);

    //ELIMINAR
    void delete(Long id);
}
