package com.nova.talentnova.service;

import com.nova.talentnova.dto.WorkAreaRequestDto;
import com.nova.talentnova.dto.WorkAreaResponseDto;

import java.util.List;

public interface IWorkAreaService {

    // LISTAR LAS ÁREAS DE TRABAJO
    List<WorkAreaResponseDto> findAll();

    // BUSCAR POR ID
    WorkAreaResponseDto findById(Integer id);

    // REGISTRAR NUEVA ÁREA DE TRABAJO
    WorkAreaResponseDto registerWorkArea(WorkAreaRequestDto dto);

    // ACTUALIZAR POR ID
    WorkAreaResponseDto updateWorkArea(Integer id, WorkAreaRequestDto dto);

    // ELIMINAR O DESACTIVAR UN ÁREA DE TRABAJO
    void deleteWorkArea(Integer id);
}