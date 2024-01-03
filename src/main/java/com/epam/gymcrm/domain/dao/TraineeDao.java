package com.epam.gymcrm.domain.dao;

import com.epam.gymcrm.domain.model.Trainee;

import java.util.List;

public interface TraineeDao {
    Trainee get(int id);
    List<Trainee> getAll();
    Trainee createTrainer(Trainee trainer);
}
