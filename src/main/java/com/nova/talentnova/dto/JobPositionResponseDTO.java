package com.nova.talentnova.dto;

import com.nova.talentnova.GeneralStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionResponseDTO {

    private Long id;
    private String name;
    private String description;
    private GeneralStatus status;
    private WorkAreaResponseDto workArea;
}