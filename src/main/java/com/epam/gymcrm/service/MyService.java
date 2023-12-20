package com.epam.gymcrm.service;

import com.epam.gymcrm.model.Trainer;
import com.epam.gymcrm.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MyService {



    @Autowired
    public TrainerRepository trainerRepository;

    public void doSomething() {
        // Business logic implementation
        System.out.println("Doing something in MyService...");
        Trainer trainer = new Trainer(1,"Jugador");

        trainerRepository.add(trainer);

        trainerRepository.getAll();



    }
}
