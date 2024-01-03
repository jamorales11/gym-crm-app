package com.epam.gymcrm.domain.dao;

import com.epam.gymcrm.domain.model.Training;

public interface TrainingDao {

    Training get(int id);

    Training createTraining(Training training);
}
