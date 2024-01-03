package com.epam.gymcrm.domain.dto;

import com.epam.gymcrm.domain.model.Trainer;
import lombok.Builder;

import java.util.ArrayList;

@Builder
public class TraineeDto {

    private Integer traineeId;


    private String dateOfBirth;
    private String address;

    private UserDto userDto;

    private ArrayList<Trainer> trainers;

    public TraineeDto() {
    }

    public TraineeDto(Integer traineeId, String dateOfBirth, String address, UserDto userDto, ArrayList<Trainer> trainers) {
        this.traineeId = traineeId;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userDto = userDto;
        this.trainers = trainers;
    }

    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Integer traineeId) {
        this.traineeId = traineeId;
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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(ArrayList<Trainer> trainers) {
        this.trainers = trainers;
    }
}
