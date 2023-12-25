package com.epam.gymcrm.dao.daoImpl;

import com.epam.gymcrm.dao.TrainerDao;
import com.epam.gymcrm.model.Trainer;
import com.epam.gymcrm.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TrainerDaoImpl implements TrainerDao {

    @Autowired
    public Storage storage;


    @Override
    public Trainer get(int id) {
        return null;
    }

    @Override
    public List<Trainer> getAll() {
        List trainers =  Arrays.asList(storage.getStorage().get("Trainer").values());
        return trainers;
    }

    @Override
    public void createTrainer(Trainer trainer) {
        Map<Integer, Object> trainers = storage.getStorage().get("Trainer");
        trainers.put(trainer.getId(),trainer);
        storage.getStorage().put("Trainer",trainers);
        System.out.println(storage.getStorage());

    }
}
