package com.epam.gymcrm.application.dto.trainer;

import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeDto;
import com.epam.gymcrm.application.dto.UserDto;
import lombok.*;

import java.util.ArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {

    private TrainingTypeDto specialization;

    private UserDto userDto;

    private ArrayList<TrainerTraineeListDTO> trainees;

}
