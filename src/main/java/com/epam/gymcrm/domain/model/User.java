package com.epam.gymcrm.domain.model;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Boolean isActive;


}
