package com.epam.gymcrm.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginDto {

    @NotNull(message = "username may not be null")
    String username;

    @NotNull(message = "password may not be null")
    String password;

}
