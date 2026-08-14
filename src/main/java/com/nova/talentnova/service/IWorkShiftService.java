package com.nova.talentnova.service;

import com.nova.talentnova.dto.WorkShiftRequestDTO;
import com.nova.talentnova.dto.WorkShiftResponseDTO;

import java.util.List;

public interface IWorkShiftService {

    //LISTAR
    List<WorkShiftResponseDTO> findAll();

    //BUSCAR POR ID
    WorkShiftResponseDTO findById(Long id);

    //REGISTRAR
    WorkShiftResponseDTO register(WorkShiftRequestDTO dto);

    //ACTUALIZAR
    WorkShiftResponseDTO update(Long id, WorkShiftRequestDTO dto);

    //ELIMINAR
    void delete(Long id);
}