package com.epam.gymcrm.application.dto.trainee;

import com.epam.gymcrm.application.dto.UserDto;
import lombok.*;

import java.util.ArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDto {

    private String dateOfBirth;

    private String address;

    private UserDto userDto;

    private ArrayList<TraineeTrainerListDTO> trainers;

}
