package com.epam.gymcrm.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTraineeProfileDto extends UserDto {

    private String dateOfBirth;
    private String address;


    public CreateTraineeProfileDto(int id, String firstName, String lastName, boolean isActive, String dateOfBirth, String address) {
        super(id, firstName, lastName, isActive);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }


}
