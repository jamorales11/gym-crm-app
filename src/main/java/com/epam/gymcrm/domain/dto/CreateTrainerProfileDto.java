package com.epam.gymcrm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class CreateTrainerProfileDto {

    private String firstName;

    private String lastName;

    private String specialization;


}
