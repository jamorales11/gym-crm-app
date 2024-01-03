package com.epam.gymcrm.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserDto {

    private int id;

    private String firstName;

    private String lastName;


    private boolean isActive;


    public UserDto() {
    }

    public UserDto(int id, String firstName, String lastName, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
    }


}
