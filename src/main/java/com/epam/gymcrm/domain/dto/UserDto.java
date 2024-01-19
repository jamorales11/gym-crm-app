package com.epam.gymcrm.domain.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private boolean isActive;

}
