package com.epam.gymcrm.adapters.controller;

import com.epam.gymcrm.adapters.dto.CreateTrainerProfileDto;
import com.epam.gymcrm.adapters.dto.TrainerDto;
import com.epam.gymcrm.application.TrainerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class TrainerController {


    private TrainerFacade trainerFacade;

    @Autowired
    public void setGymFacade(TrainerFacade trainerFacade) {
        this.trainerFacade = trainerFacade;
    }

    @PostMapping("/createTrainerProfile")
    ResponseEntity<TrainerDto> createTrainerProfile(@RequestBody CreateTrainerProfileDto createTrainerProfileDto) {
        //… your code return book;
        TrainerDto trainerDtoCreated = trainerFacade.createTrainerProfile(createTrainerProfileDto);
        return new ResponseEntity<>(trainerDtoCreated, HttpStatus.CREATED);
    }


}
