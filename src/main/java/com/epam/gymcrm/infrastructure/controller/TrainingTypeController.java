package com.epam.gymcrm.infrastructure.controller;

import com.epam.gymcrm.application.TrainingTypeService;
import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeDto;
import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Training", description = "the Training Api")
@RestController
@RequestMapping(value = "/api/trainingType")
public class TrainingTypeController {

    private final TrainingTypeService trainingTypeService;



    @Autowired
    public TrainingTypeController(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    @Operation(summary = "Get Training Types", description = "Retrieves Training Types")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all Training Types")
    @GetMapping
    ResponseEntity<List<TrainingTypeListDto>> getTrainingTypeList() {

        try {

            List<TrainingTypeListDto> trainingTypeDtos = trainingTypeService.getTrainingTypeList();
            return new ResponseEntity<>(trainingTypeDtos, HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }




}
