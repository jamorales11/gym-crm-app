package com.epam.gymcrm.repository;

import com.epam.gymcrm.dao.TrainerDao;
import com.epam.gymcrm.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainerRepositoryImpl implements TrainerRepository{
    @Autowired
    TrainerDao trainerDao;

    @Override
    public void add(Trainer trainer) {
        trainerDao.createTrainer(trainer);
    }

    public List<Trainer> getAll(){
        return trainerDao.getAll();
    }
}
