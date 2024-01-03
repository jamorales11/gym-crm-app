package com.epam.gymcrm.infrastructure.daoImpl;

import com.epam.gymcrm.config.storage.Storage;
import com.epam.gymcrm.domain.dao.TraineeDao;
import com.epam.gymcrm.domain.model.Trainee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class TraineeDaoImpl implements TraineeDao {

    public Storage storage;

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Trainee get(int id) {
        return (Trainee) storage.getStorage().get("Trainee").get(id);
    }

    @Override
    public List<Trainee> getAll() {
        List trainees = Arrays.asList(storage.getStorage().get("Trainee").values().toArray());
        return trainees;
    }

    @Override
    public Trainee createTrainer(Trainee trainee) {
        Map<Integer, Object> trainees = storage.getStorage().get("Trainee");
        trainees.put(trainee.getTraineeId(), trainee);
        storage.getStorage().put("Trainee", trainees);

        log.info("Trainee has been saved to the storage");

        return trainee;
    }
}
