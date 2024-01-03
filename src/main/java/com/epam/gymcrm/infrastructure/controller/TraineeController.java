package com.epam.gymcrm.infrastructure.controller;

import com.epam.gymcrm.application.TraineeService;
import com.epam.gymcrm.domain.dto.CreateTraineeProfileDto;
import com.epam.gymcrm.domain.dto.TraineeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/trainee")
public class TraineeController {


    private TraineeService traineeService;

    @Autowired
    public void setGymFacade(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping
    ResponseEntity<TraineeDto> createTraineeProfile(@RequestBody CreateTraineeProfileDto createTraineeProfileDto) {

        TraineeDto traineeDtoCreated = traineeService.createTraineeProfile(createTraineeProfileDto);
        return new ResponseEntity<>(traineeDtoCreated, HttpStatus.CREATED);
    }


}
