package com.nova.talentnova.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkShiftResponseDTO {

    private Long id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
}