package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.domain.dto.TrainerDto;
import com.epam.gymcrm.domain.model.Trainer;

import java.util.List;

public interface TrainerRepository {

    TrainerDto createTrainer(TrainerDto trainerDto);

    List<Trainer> getAll();

    Trainer get(int id);

    void updateTrainer();
}
