package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.domain.model.Trainee;

import java.util.List;

public interface TraineeRepository {

    Trainee createTrainee(Trainee traineeDto);

    List<Trainee> getAll();

    Trainee get(int id);

    void updateTrainer();
}
