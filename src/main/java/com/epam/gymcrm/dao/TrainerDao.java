package com.epam.gymcrm.dao;

import com.epam.gymcrm.model.Trainer;

import java.util.List;
import java.util.Map;

public interface TrainerDao {
    Trainer get(int id);
    List<Trainer> getAll();
    void createTrainer(Trainer trainer);

}
