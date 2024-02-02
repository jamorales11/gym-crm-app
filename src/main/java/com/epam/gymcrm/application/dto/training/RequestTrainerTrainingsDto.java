package com.epam.gymcrm.application.dto.training;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTrainerTrainingsDto {

    @NotNull(message = "username may not be null")
    String username;

    @NotNull(message = "password may not be null")
    String password;

    private Date periodFrom;

    private Date periodTo;

    private String traineeName;

    private String TrainingType;

}
