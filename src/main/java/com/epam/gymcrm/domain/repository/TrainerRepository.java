package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.domain.model.Trainer;

import java.util.List;

public interface TrainerRepository {

    Trainer createTrainer(Trainer trainer);

    List<Trainer> getAll();

    Trainer get(int id);

    Trainer updateTrainer(Trainer trainer);
}
