package com.epam.gymcrm.infrastructure.repositoryImpl;

import com.epam.gymcrm.domain.dao.TrainerDao;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TrainerRepositoryImpl implements TrainerRepository {

    private TrainerDao trainerDao;


    public TrainerRepositoryImpl(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {

        Trainer trainerCreated = trainerDao.createTrainer(trainer);

        log.debug("Trainee with id: " + trainerCreated.getTrainerId() + " has been created.");

        return trainerCreated;
    }

    @Override
    public List<Trainer> getAll() {
        return trainerDao.getAll();
    }

    @Override
    public Trainer get(int id) {
        return trainerDao.get(id);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {

        Trainer trainerCreated = trainerDao.createTrainer(trainer);

        log.debug("Trainee with id: " + trainerCreated.getTrainerId() + " has been updated.");

        return trainerCreated;

    }

}
