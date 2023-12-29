package com.epam.gymcrm.domain.model;

import lombok.Builder;

import java.util.ArrayList;

@Builder
public class Trainer {

    private Integer trainerId;

    private String specialization;

    private Integer userId;

    private ArrayList<Trainee> trainees;

    public Trainer(Integer trainerId, String specialization, Integer userId, ArrayList<Trainee> trainees) {
        this.trainerId = trainerId;
        this.specialization = specialization;
        this.userId = userId;
        this.trainees = new ArrayList<>();
    }

    public Trainer(){

    }


    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
