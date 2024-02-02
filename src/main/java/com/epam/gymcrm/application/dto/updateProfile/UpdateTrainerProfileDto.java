package com.epam.gymcrm.application.dto.updateProfile;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTrainerProfileDto {

    @NotNull(message = "username may not be null")

    String username;

    @NotNull(message = "password may not be null")
    String password;

    @NotNull(message = "firstName may not be null")
    private String firstName;

    @NotNull(message = "lastName may not be null")
    private String lastName;

    @NotNull(message = "specialization may not be null")
    private String specialization;


}
