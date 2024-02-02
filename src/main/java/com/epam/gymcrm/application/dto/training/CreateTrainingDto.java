package com.epam.gymcrm.application.dto.training;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrainingDto {

    private String name;

    private Date date;

    private int duration;

    private String traineeUsername;

    private String traineePassword;

    private String trainerUsername;

}
