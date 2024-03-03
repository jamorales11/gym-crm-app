package com.epam.gymcrm.infrastructure.controller;

import com.epam.gymcrm.application.TraineeService;
import com.epam.gymcrm.application.TrainingService;
import com.epam.gymcrm.application.dto.training.*;
import com.epam.gymcrm.domain.exceptions.WrongCredentialsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Training", description = "the Training Api")
@RestController
@RequestMapping(value = "/api/training")
public class TrainingController {

    private final TrainingService trainingService;

    private final TraineeService traineeService;




    @Autowired
    public TrainingController(TrainingService trainingService, TraineeService traineeService) {
        this.trainingService = trainingService;
        this.traineeService = traineeService;
    }

    @Operation(summary = "Add Training", description = "A")
    //@ApiResponse(responseCode = "404", description = "foo")
    @PostMapping
    ResponseEntity<?> createTraining(@RequestBody @Valid CreateTrainingDto trainingDto) {

        try {


            traineeService.traineeLogin(trainingDto.getTraineeUsername(),trainingDto.getTraineePassword());

            trainingService.createTraining(trainingDto);
            return new ResponseEntity<>("Training Created", HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @Operation(summary = "Get Trainee Trainings List", description = "Retrieves Trainee Trainings")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Trainee Trainings List")
    @GetMapping("/traineeTrainings")
    ResponseEntity<List<ResponseTraineeTrainingDto>> getTraineeTrainings(@RequestBody @Valid RequestTraineeTrainingsDto requestTraineeTrainingsDto){
        try {
            traineeService.traineeLogin(requestTraineeTrainingsDto.getUsername(),requestTraineeTrainingsDto.getPassword());

            List<ResponseTraineeTrainingDto> responseTraineeTrainingDto = trainingService.getTraineeTrainings(requestTraineeTrainingsDto);

            return new ResponseEntity<>(responseTraineeTrainingDto, HttpStatus.OK);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @Operation(summary = "Get Trainer Trainings List", description = "Retrieves Trainer Trainings")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Trainer Trainings List")
    @GetMapping("/trainerTrainings")
    ResponseEntity<List<ResponseTrainerTrainingDto>> getTraineeTrainings(@RequestBody @Valid RequestTrainerTrainingsDto requestTrainerTrainingsDto){
        try {
            traineeService.traineeLogin(requestTrainerTrainingsDto.getUsername(),requestTrainerTrainingsDto.getPassword());

            List<ResponseTrainerTrainingDto> responseTrainerTrainingDtos = trainingService.getTrainerTrainings(requestTrainerTrainingsDto);

            return new ResponseEntity<>(responseTrainerTrainingDtos, HttpStatus.OK);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }



}
