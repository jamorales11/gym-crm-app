package com.epam.gymcrm.infrastructure.repository;

import com.epam.gymcrm.domain.model.Training;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository {

    Training get(int id);

    Training createTraining(Training training);
}
