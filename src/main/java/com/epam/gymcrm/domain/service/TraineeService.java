package com.epam.gymcrm.domain.service;

import com.epam.gymcrm.adapters.dto.TraineeDto;
import com.epam.gymcrm.domain.model.Trainee;

import java.util.List;

public interface TraineeService {

    TraineeDto createTrainee(TraineeDto traineeDto);

    List<Trainee> getAll();

    Trainee get(int id);

    void updateTrainer();
}
