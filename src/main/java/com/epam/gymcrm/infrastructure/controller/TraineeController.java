package com.epam.gymcrm.infrastructure.controller;

import com.epam.gymcrm.application.TraineeService;
import com.epam.gymcrm.application.dto.ChangePasswordDto;
import com.epam.gymcrm.application.dto.RequestLoginDto;
import com.epam.gymcrm.application.dto.trainee.TraineeDto;
import com.epam.gymcrm.application.dto.trainee.TraineeTrainerListDTO;
import com.epam.gymcrm.application.dto.trainer.TrainerDto;
import com.epam.gymcrm.application.dto.trainee.CreateTraineeProfileDto;
import com.epam.gymcrm.application.dto.response.RegistrationResponseDTO;
import com.epam.gymcrm.application.dto.updateProfile.UpdateTraineeProfileDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.exceptions.WrongCredentialsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Trainee", description = "the Trainee Api")
@RestController
@RequestMapping(value = "/api/trainee")
public class TraineeController {

    private ModelMapper modelMapper;

    private TraineeService traineeService;

    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @Operation(summary = "Trainee Registration", description = "A")
    @PostMapping
    ResponseEntity<RegistrationResponseDTO> createTraineeProfile(@RequestBody @Valid CreateTraineeProfileDto createTraineeProfileDto) {
        try {
            User userToCreate = modelMapper.map(createTraineeProfileDto, User.class);
            Trainee traineeToCreate = modelMapper.map(createTraineeProfileDto, Trainee.class);

            RegistrationResponseDTO registrationResponseDTO = traineeService.createTraineeProfile(userToCreate, traineeToCreate);
            return new ResponseEntity<>(registrationResponseDTO, HttpStatus.CREATED);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainee Not Created", e);
        }


    }

    @Operation(summary = "Get Trainee Profile", description = "A")
    @GetMapping
    ResponseEntity<TraineeDto> selectTrainee(@RequestBody RequestLoginDto requestLoginDto){
        try {
            traineeService.traineeLogin(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            TraineeDto traineeDto = traineeService.getTrainee(requestLoginDto.getUsername());

            return new ResponseEntity<>(traineeDto, HttpStatus.FOUND);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "Trainee Login", description = "A")
    @GetMapping("/login")
    ResponseEntity<TraineeDto> traineeLogin(@RequestBody RequestLoginDto requestLoginDto){

        try {
            traineeService.traineeLogin(requestLoginDto.getUsername(),requestLoginDto.getPassword());
            TraineeDto traineeDtoFound = traineeService.traineeLogin(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            return new ResponseEntity<>(traineeDtoFound,HttpStatus.FOUND);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }



    @Operation(summary = "A", description = "A")
    @GetMapping("/trainers")
    ResponseEntity<List<TrainerDto>> getTrainerList(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {
            traineeService.traineeLogin(requestLoginDto.getUsername(),requestLoginDto.getPassword());
            List<TrainerDto> trainerDtos = traineeService.getTrainerList(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>(trainerDtos, HttpStatus.FOUND);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "Get not assigned on trainee active trainers", description = "A")
    @GetMapping("/notAssignedTrainers")
    ResponseEntity<List<TraineeTrainerListDTO>> getNotAssignedOnTrainerList(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {
            traineeService.traineeLogin(requestLoginDto.getUsername(),requestLoginDto.getPassword());
            List<TraineeTrainerListDTO> trainerDtos = traineeService.getNotAssignedOnTrainers(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            return new ResponseEntity<>(trainerDtos, HttpStatus.FOUND);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "Change Trainee Login", description = "A")
    @PatchMapping("/password")
    ResponseEntity<?> changeTraineePassword(@RequestBody ChangePasswordDto changePasswordDto){
        try {
            traineeService.traineeLogin(changePasswordDto.getUsername(),changePasswordDto.getPassword());

            traineeService.changePassword(changePasswordDto.getUsername(),changePasswordDto.getPassword(), changePasswordDto.getNewPassword());

            return new ResponseEntity<>("Trainee password updated", HttpStatus.OK);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "Deactivate Trainee", description = "A")
    @PatchMapping("/deactivate")
    ResponseEntity<?> deactivateTrainee(@RequestBody RequestLoginDto requestLoginDto){
        try {
            traineeService.traineeLogin(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            traineeService.deactivateTrainee(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            return new ResponseEntity<>("Trainee deactivated", HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "Activate Trainee", description = "A")
    @PatchMapping("/activate")
    ResponseEntity<?> activateTrainee(@RequestBody RequestLoginDto requestLoginDto){
        try {
            traineeService.traineeLogin(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            traineeService.activateTrainee(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            return new ResponseEntity<>("Trainee activated", HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "Update Trainee profile", description = "A")
    @PatchMapping
    ResponseEntity<TraineeDto> updateTraineeProfile(@RequestBody @Valid UpdateTraineeProfileDto updateTraineeProfileDto){
        try {

            traineeService.traineeLogin(updateTraineeProfileDto.getUsername(),updateTraineeProfileDto.getPassword());

            TraineeDto traineeDto = traineeService.updateTraineeProfile(updateTraineeProfileDto);

            return new ResponseEntity<>(traineeDto, HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "Delete Trainee Profile", description = "A")
    @DeleteMapping
    ResponseEntity<?> deleteTraineeProfile(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {
            traineeService.traineeLogin(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            traineeService.deleteTrainee(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>("Trainee deleted", HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }



    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


}
