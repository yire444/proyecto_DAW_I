package com.nova.talentnova.service;

import com.nova.talentnova.dto.WorkAreaRequestDto;
import com.nova.talentnova.dto.WorkAreaResponseDto;

import java.util.List;

public interface IWorkAreaService {

    //LISTAR POR EMPRESA
    List<WorkAreaResponseDto> getAllWorkAreasByCompany(Long companyId);

    //REGISTRAR ÁREA DE TRABAJO POR EMPRESA
    WorkAreaResponseDto createWorkArea(WorkAreaRequestDto requestDto, Long companyId);

    //ACTUALIZAR ÁREA DE TRABAJO POR EMPRESA
    WorkAreaResponseDto updateWorkArea(Long id, WorkAreaRequestDto requestDto, Long companyId);

    //ELIMINAR POR EMPRESA
    void deleteWorkArea(Long id, Long companyId);
}