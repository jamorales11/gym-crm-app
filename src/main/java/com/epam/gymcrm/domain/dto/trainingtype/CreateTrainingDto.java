package com.epam.gymcrm.domain.dto.trainingtype;

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

    private int traineeUsername;

    private int trainerUsername;

    private int trainingTypeId;


}
