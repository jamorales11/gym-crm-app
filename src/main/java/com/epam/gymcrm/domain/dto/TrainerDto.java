package com.epam.gymcrm.domain.dto;

import com.epam.gymcrm.domain.model.Trainee;
import lombok.*;

import java.util.ArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {

    private Integer trainerId;

    private String specialization;

    private UserDto userDto;

    private ArrayList<Trainee> trainees;

}
