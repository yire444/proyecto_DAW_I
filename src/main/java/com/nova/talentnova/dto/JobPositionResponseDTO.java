package com.nova.talentnova.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private Boolean status;
}