package com.epam.gymcrm.domain.ports.dao;

import com.epam.gymcrm.domain.model.Trainer;

import java.util.List;

public interface TrainerDao {
    Trainer get(int id);
    List<Trainer> getAll();
    Trainer createTrainer(Trainer trainer);

}
