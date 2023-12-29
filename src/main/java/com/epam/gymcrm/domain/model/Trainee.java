package com.epam.gymcrm.domain.model;

import java.util.ArrayList;
import java.util.Date;

public class Trainee {

    private Integer traineeId;

    private String dateOfBirth;
    private String address;

    private Integer userId;

    private ArrayList<Trainer> trainers;

    public Trainee(Integer traineeId, String dateOfBirth, String address, Integer userId, ArrayList<Trainer> trainers) {
        this.traineeId = traineeId;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userId = userId;
        this.trainers = trainers;
    }


    public Trainee() {
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


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(ArrayList<Trainer> trainers) {
        this.trainers = trainers;
    }
}
