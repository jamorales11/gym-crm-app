package com.epam.gymcrm.application.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private String firstName;

    private String lastName;

    private boolean isActive;

}
