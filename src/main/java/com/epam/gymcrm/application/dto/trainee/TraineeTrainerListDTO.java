package com.epam.gymcrm.application.dto.trainee;

import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeDto;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TraineeTrainerListDTO {

    private String username;

    private String firstName;

    private String lastName;

    private TrainingTypeDto specialization;

}
