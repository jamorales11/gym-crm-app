package com.epam.gymcrm.infrastructure.controller;

import com.epam.gymcrm.application.TrainerService;
import com.epam.gymcrm.domain.dto.CreateTrainerProfileDto;
import com.epam.gymcrm.domain.dto.TrainerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/trainer")
public class TrainerController {


    private TrainerService trainerService;

    @Autowired
    public void setGymFacade(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    ResponseEntity<TrainerDto> createTrainerProfile(@RequestBody CreateTrainerProfileDto createTrainerProfileDto) {

        TrainerDto trainerDtoCreated = trainerService.createTrainerProfile(createTrainerProfileDto);
        return new ResponseEntity<>(trainerDtoCreated, HttpStatus.CREATED);
    }


}
