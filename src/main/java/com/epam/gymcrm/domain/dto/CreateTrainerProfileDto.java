package com.epam.gymcrm.domain.dto;

public class CreateTrainerProfileDto extends UserDto{

    private String specialization;

    public CreateTrainerProfileDto(int id, String firstName, String lastName, boolean isActive, String specialization) {
        super(id, firstName, lastName, isActive);
        this.specialization = specialization;
    }




    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
