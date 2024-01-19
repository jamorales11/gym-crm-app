package com.epam.gymcrm.domain.dto.createProfile;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrainerProfileDto {

    @NotNull(message = "firstName may not be null")
    private String firstName;

    @NotNull(message = "lastName may not be null")
    private String lastName;

    @NotNull(message = "specialization may not be null")
    private String specialization;


}
