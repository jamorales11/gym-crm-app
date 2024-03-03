package com.epam.gymcrm.application.dto.training;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTraineeTrainingDto {

    private String name;

    private Date date;

    private int duration;

    private String trainerName;

    private String trainingType;


}
