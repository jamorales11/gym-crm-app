package com.epam.gymcrm.adapters.controller;

import com.epam.gymcrm.adapters.dto.CreateTraineeProfileDto;
import com.epam.gymcrm.adapters.dto.TraineeDto;
import com.epam.gymcrm.application.TraineeFacade;
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


    private TraineeFacade traineeFacade;

    public void setGymFacade(TraineeFacade traineeFacade) {
        this.traineeFacade = traineeFacade;
    }

    @PostMapping
    ResponseEntity<TraineeDto> createTraineeProfile(@RequestBody CreateTraineeProfileDto createTraineeProfileDto) {

        TraineeDto traineeDtoCreated = traineeFacade.createTraineeProfile(createTraineeProfileDto);
        return new ResponseEntity<>(traineeDtoCreated, HttpStatus.CREATED);
    }


}
