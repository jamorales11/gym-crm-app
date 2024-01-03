package com.epam.gymcrm.infrastructure.repositoryImpl;

import com.epam.gymcrm.domain.dao.TraineeDao;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.repository.TraineeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TraineeRepositoryImpl implements TraineeRepository {


    public TraineeDao traineeDao;


    public TraineeRepositoryImpl(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }


    @Override
    public Trainee createTrainee(Trainee trainee) {

        Trainee traineeCreated = traineeDao.createTrainer(trainee);

        log.debug("Trainee with id: " + traineeCreated.getTraineeId() + " has been created.");

        return traineeCreated;
    }

    @Override
    public List<Trainee> getAll() {
        return traineeDao.getAll();
    }

    @Override
    public Trainee get(int id) {
        return traineeDao.get(id);
    }

    @Override
    public void updateTrainer() {

    }


}
