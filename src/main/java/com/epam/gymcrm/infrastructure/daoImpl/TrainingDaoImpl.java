package com.epam.gymcrm.infrastructure.daoImpl;

import com.epam.gymcrm.config.storage.Storage;
import com.epam.gymcrm.domain.dao.TrainingDao;
import com.epam.gymcrm.domain.model.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Slf4j
public class TrainingDaoImpl implements TrainingDao {

    public Storage storage;

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Training get(int id) {
        return (Training) storage.getStorage().get("Training").get(id);
    }

    @Override
    public Training createTraining(Training training) {
        Map<Integer, Object> trainings = storage.getStorage().get("Training");
        trainings.put(training.getTrainingId(), training);
        storage.getStorage().put("Training", trainings);

        log.info("Training has been saved to the storage");

        return training;
    }
}
