package com.epam.gymcrm.adapters.dto;

public class CreateTrainerProfileDto extends UserDto{

    private String specialization;

    public CreateTrainerProfileDto(int id, String firstName, String lastName, String username, String password, boolean isActive, String specialization) {
        super(id, firstName, lastName, username, password, isActive);
        this.specialization = specialization;
    }




    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
