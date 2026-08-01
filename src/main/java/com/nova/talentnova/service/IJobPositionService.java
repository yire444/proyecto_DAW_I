package com.nova.talentnova.service;

import com.nova.talentnova.dto.JobPositionRequestDTO;
import com.nova.talentnova.dto.JobPositionResponseDTO;

import java.util.List;

public interface IJobPositionService {

    //LISTAR LOS PUESTOS DE TRABAJO
    List<JobPositionResponseDTO> findAll();

    //BUSCAR EL PUESTO DE TRABAJO POR ID
    JobPositionResponseDTO findById(Integer id);

    //REGISTRAR PUESTO DE TRABAJO
    JobPositionResponseDTO registerJobPosition(JobPositionRequestDTO dto);

    //ACTUALIZAR PUESTO DE TRABAJO
    JobPositionResponseDTO updateJobPosition(Integer id, JobPositionRequestDTO dto);

    //ELIMINAR PUESTO DE TRABAJO
    void deleteJobPosition(Integer id);
}