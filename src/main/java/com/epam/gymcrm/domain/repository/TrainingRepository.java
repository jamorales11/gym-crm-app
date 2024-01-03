package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.domain.model.Training;

public interface TrainingRepository {

    Training get(int id);

    Training createTraining(Training training);
}
