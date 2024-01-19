package com.epam.gymcrm.domain.dto;

import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.infrastructure.entity.TrainingTypeEntity;
import lombok.*;

import java.util.ArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {

    private Integer trainerId;

    private TrainingTypeDto specialization;

    private UserDto userDto;

    private ArrayList<Trainee> trainees;

}
