package com.epam.gymcrm.infrastructure.repositoryImpl;

import com.epam.gymcrm.domain.dao.TrainingDao;
import com.epam.gymcrm.domain.model.Training;
import com.epam.gymcrm.domain.repository.TrainingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class TrainingRepositoryImpl implements TrainingRepository {

    public TrainingDao trainingDao;

    public TrainingRepositoryImpl(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Override
    public Training get(int id) {
        return trainingDao.get(id);
    }

    @Override
    public Training createTraining(Training training) {
        Training trainingCreated = trainingDao.createTraining(training);

        log.debug("Training with id: " + trainingCreated.getTrainingId() + " has been created.");

        return trainingCreated;
    }
}
