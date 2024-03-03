package com.epam.gymcrm.application.dto.trainee;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTraineeProfileDto {

    @NotNull(message = "firstName may not be null")
    private String firstName;

    @NotNull(message = "lastName may not be null")
    private String lastName;


    private String dateOfBirth;


    private String address;


}
