package com.epam.gymcrm.domain.dto;

import com.epam.gymcrm.domain.model.Trainee;
import lombok.Builder;

import java.util.ArrayList;

@Builder
public class TrainerDto {

    private Integer trainerId;

    private String specialization;

    private UserDto userDto;

    private ArrayList<Trainee> trainees;

    public TrainerDto() {
    }

    public TrainerDto(Integer trainerId, String specialization, UserDto userDto, ArrayList<Trainee> trainees) {
        this.trainerId = trainerId;
        this.specialization = specialization;
        this.userDto = userDto;
        this.trainees = trainees;
    }


    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }



    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }



    public ArrayList<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(ArrayList<Trainee> trainees) {
        this.trainees = trainees;
    }
}
