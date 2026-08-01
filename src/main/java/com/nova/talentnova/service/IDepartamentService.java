package com.nova.talentnova.service;

import com.nova.talentnova.dto.DepartamentRequestDTO;
import com.nova.talentnova.dto.DepartamentResponseDTO;

import java.util.List;

public interface IDepartamentService {

    //LISTAR LOS DEPARTAMENTOS DE TRABAJO
    List<DepartamentResponseDTO> findAll();

    //BUSCAR POR ID
    DepartamentResponseDTO findById(Integer id);

    //REGISTRAR NUEVO DEPARTAMENTO DE TRABAJO
    DepartamentResponseDTO registerDepartament(DepartamentRequestDTO dto);

    //ACTUALIZAR POR ID
    DepartamentResponseDTO updateDepartament(Integer id, DepartamentRequestDTO dto);

    //ELIMINAR UN DEPARTAMENTO DE TRABAJO
    void deleteDepartament(Integer id);
}