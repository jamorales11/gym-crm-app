package com.epam.gymcrm.application.dto.trainer;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerTraineeListDTO {

    private String username;

    private String firstName;

    private String lastName;

}
