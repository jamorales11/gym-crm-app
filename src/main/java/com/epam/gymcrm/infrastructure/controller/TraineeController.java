package com.epam.gymcrm.infrastructure.controller;

import com.epam.gymcrm.application.TraineeService;
import com.epam.gymcrm.domain.dto.ChangePasswordDto;
import com.epam.gymcrm.domain.dto.RequestLoginDto;
import com.epam.gymcrm.domain.dto.TraineeDto;
import com.epam.gymcrm.domain.dto.TrainerDto;
import com.epam.gymcrm.domain.dto.createProfile.CreateTraineeProfileDto;
import com.epam.gymcrm.domain.dto.updateProfile.UpdateTraineeProfileDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.exceptions.WrongCredentialsException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/trainee")
public class TraineeController {

    private ModelMapper modelMapper;

    private TraineeService traineeService;

    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping
    ResponseEntity<TraineeDto> createTraineeProfile(@RequestBody @Valid CreateTraineeProfileDto createTraineeProfileDto) {
        try {
            User userToCreate = modelMapper.map(createTraineeProfileDto, User.class);
            Trainee traineeToCreate = modelMapper.map(createTraineeProfileDto, Trainee.class);

            TraineeDto traineeDtoCreated = traineeService.createTraineeProfile(userToCreate, traineeToCreate);
            return new ResponseEntity<>(traineeDtoCreated, HttpStatus.CREATED);

        } catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }


    }

    @GetMapping
    ResponseEntity<TraineeDto> selectTrainee(@RequestBody RequestLoginDto requestLoginDto){
        try {

            TraineeDto traineeDto = traineeService.getTrainee(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>(traineeDto, HttpStatus.FOUND);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("login")
    ResponseEntity<TraineeDto> traineeLogin(@RequestBody RequestLoginDto requestLoginDto){

        try {

            TraineeDto traineeDtoFound = traineeService.traineeLogin(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            return new ResponseEntity<>(traineeDtoFound,HttpStatus.FOUND);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        }
    }

    @GetMapping("(trainers")
    ResponseEntity<List<TrainerDto>> getTrainerList(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {
            List<TrainerDto> trainerDtos = traineeService.getTrainerList(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>(trainerDtos, HttpStatus.FOUND);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }







    @PatchMapping("/password")
    ResponseEntity<?> changeTraineePassword(@RequestBody ChangePasswordDto changePasswordDto){
        try {
            traineeService.changePassword(changePasswordDto.getUsername(),changePasswordDto.getPassword(), changePasswordDto.getNewPassword());

            return new ResponseEntity<>("Trainee password updated", HttpStatus.OK);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }



    @PatchMapping("/deactivate")
    ResponseEntity<?> deactivateTrainee(@RequestBody RequestLoginDto requestLoginDto){
        try {
            traineeService.deactivateTrainee(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            return new ResponseEntity<>("Trainee deactivated", HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @PatchMapping("/activate")
    ResponseEntity<?> activateTrainee(@RequestBody RequestLoginDto requestLoginDto){
        try {
            traineeService.activateTrainee(requestLoginDto.getUsername(),requestLoginDto.getPassword());

            return new ResponseEntity<>("Trainee activated", HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @PatchMapping
    ResponseEntity<TraineeDto> updateTraineeProfile(@RequestBody @Valid UpdateTraineeProfileDto updateTraineeProfileDto){
        try {
            TraineeDto traineeDto = traineeService.updateTraineeProfile(updateTraineeProfileDto);

            return new ResponseEntity<>(traineeDto, HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping
    ResponseEntity<?> deleteTraineeProfile(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {
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
