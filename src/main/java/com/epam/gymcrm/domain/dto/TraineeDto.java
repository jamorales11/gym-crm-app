package com.epam.gymcrm.domain.dto;

import com.epam.gymcrm.domain.model.Trainer;
import lombok.*;

import java.util.ArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDto {

    private Integer traineeId;

    private String dateOfBirth;

    private String address;

    private UserDto userDto;

    private ArrayList<Trainer> trainers;

}
