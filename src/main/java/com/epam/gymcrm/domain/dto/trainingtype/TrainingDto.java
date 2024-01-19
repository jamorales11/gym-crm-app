package com.epam.gymcrm.domain.dto.trainingtype;

import com.epam.gymcrm.domain.dto.TrainingTypeDto;
import com.epam.gymcrm.domain.dto.UserDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.infrastructure.entity.TraineeEntity;
import com.epam.gymcrm.infrastructure.entity.TrainerEntity;
import com.epam.gymcrm.infrastructure.entity.TrainingTypeEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {

    private int id;

    private String name;

    private Date date;

    private int duration;

    private TraineeEntity trainee;

    private TrainerEntity trainer;

    private TrainingTypeEntity trainingType;


}
