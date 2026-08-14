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
}package com.nova.talentnova.service.impl;

import com.nova.talentnova.dto.BankRequestDTO;
import com.nova.talentnova.dto.BankResponseDTO;
import com.nova.talentnova.mapper.BankMapper;
import com.nova.talentnova.model.Bank;
import com.nova.talentnova.repository.IBankRepository;
import com.nova.talentnova.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

