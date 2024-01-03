package com.epam.gymcrm.infrastructure.daoImpl;

import com.epam.gymcrm.config.storage.Storage;
import com.epam.gymcrm.domain.dao.TrainerDao;
import com.epam.gymcrm.domain.model.Trainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Repository
@Slf4j
public class TrainerDaoImpl implements TrainerDao {

    public Storage storage;

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Trainer get(int id) {
        return (Trainer) storage.getStorage().get("Trainer").get(id);
    }

    @Override
    public List<Trainer> getAll() {

        List trainers = Arrays.asList(storage.getStorage().get("Trainer").values().toArray());
        return trainers;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        Map<Integer, Object> trainers = storage.getStorage().get("Trainer");
        trainers.put(trainer.getTrainerId(), trainer);
        storage.getStorage().put("Trainer", trainers);

        log.info("Trainer has been saved to the storage");

        return trainer;
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        Map<Integer, Object> trainers = storage.getStorage().get("Trainer");
        trainers.put(trainer.getTrainerId(), trainer);
        storage.getStorage().put("Trainer", trainers);

        log.info("Trainer has been updated in storage");

        return trainer;
    }
}
