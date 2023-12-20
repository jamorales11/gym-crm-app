package com.epam.gymcrm.repository;


import com.epam.gymcrm.model.Trainer;

import java.util.List;
import java.util.Map;

public interface TrainerRepository {

    void add(Trainer trainer);
    List<Trainer> getAll();
}
