package com.epam.gymcrm.domain.service;

import com.epam.gymcrm.adapters.dto.TrainerDto;
import com.epam.gymcrm.domain.model.Trainer;

import java.util.List;

public interface TrainerService {

    TrainerDto createTrainer(TrainerDto trainerDto);

    List<Trainer> getAll();

    Trainer get(int id);

    void updateTrainer();
}
