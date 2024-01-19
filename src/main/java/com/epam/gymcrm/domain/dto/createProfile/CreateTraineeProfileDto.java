package com.epam.gymcrm.domain.dto.createProfile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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
