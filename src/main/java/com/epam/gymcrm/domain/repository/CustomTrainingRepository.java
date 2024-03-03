package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.application.dto.training.RequestTraineeTrainingsDto;
import com.epam.gymcrm.application.dto.training.RequestTrainerTrainingsDto;
import com.epam.gymcrm.infrastructure.entity.TrainingEntity;

import java.util.List;

public interface CustomTrainingRepository {

    List<TrainingEntity> findTraineeTrainingsByUsernameAndCriteria(RequestTraineeTrainingsDto req);

    List<TrainingEntity> findTrainerTrainingsByUsernameAndCriteria(RequestTrainerTrainingsDto req);
}
