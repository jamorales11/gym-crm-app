package com.epam.gymcrm.adapters.dto;

import java.util.Date;

public class CreateTraineeProfileDto extends UserDto{

    private String dateOfBirth;
    private String address;


    public CreateTraineeProfileDto(int id, String firstName, String lastName, String username, String password, boolean isActive, String dateOfBirth, String address) {
        super(id, firstName, lastName, username, password, isActive);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
