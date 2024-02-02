package com.epam.gymcrm.application.dto.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDTO {

    private String username;

    private String password;

}
