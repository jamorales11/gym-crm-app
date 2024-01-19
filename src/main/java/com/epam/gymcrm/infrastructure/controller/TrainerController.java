package com.epam.gymcrm.infrastructure.controller;

import com.epam.gymcrm.application.TrainerService;
import com.epam.gymcrm.domain.dto.ChangePasswordDto;
import com.epam.gymcrm.domain.dto.RequestLoginDto;
import com.epam.gymcrm.domain.dto.TraineeDto;
import com.epam.gymcrm.domain.dto.TrainerDto;
import com.epam.gymcrm.domain.dto.createProfile.CreateTrainerProfileDto;
import com.epam.gymcrm.domain.dto.updateProfile.UpdateTrainerProfileDto;
import com.epam.gymcrm.domain.model.Trainer;
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
@RequestMapping(value = "/api/trainer")
public class TrainerController {

    private ModelMapper modelMapper;

    private TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    ResponseEntity<TrainerDto> createTrainerProfile(@RequestBody @Valid CreateTrainerProfileDto createTrainerProfileDto) {

        try {
            User userToCreate = modelMapper.map(createTrainerProfileDto, User.class);
            Trainer trainerToCreate = modelMapper.map(createTrainerProfileDto, Trainer.class);

            TrainerDto trainerDtoCreated = trainerService.createTrainerProfile(userToCreate, trainerToCreate);
            return new ResponseEntity<>(trainerDtoCreated, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer Not Created", e);
        }
    }

    @GetMapping
    ResponseEntity<TrainerDto> selectTrainer(@RequestBody RequestLoginDto requestLoginDto){
        try {
            TrainerDto trainerDto = trainerService.getTrainer(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>(trainerDto, HttpStatus.FOUND);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @GetMapping("login")
    ResponseEntity<TrainerDto> trainerLogin(@RequestBody @Valid RequestLoginDto requestLoginDto) {

        try {

            TrainerDto trainerDtoFound = trainerService.trainerLogin( requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>(trainerDtoFound, HttpStatus.OK);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }


    @GetMapping("/trainees")
    ResponseEntity<List<TraineeDto>> getTraineeList(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {

            List<TraineeDto> traineeDtos = trainerService.getTraineeList(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>(traineeDtos, HttpStatus.FOUND);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }


    @PatchMapping("/password")
    ResponseEntity<?> changeTrainerPassword(@RequestBody @Valid ChangePasswordDto changePasswordDto){

        try {
            trainerService.changePassword(changePasswordDto.getUsername(), changePasswordDto.getPassword(), changePasswordDto.getNewPassword());

            return new ResponseEntity<>("Trainer password updated", HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @PatchMapping("/deactivate")
    ResponseEntity<?> deactivateTrainer(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {
            trainerService.deactivateTrainer(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>("Trainer deactivated", HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @PatchMapping("/activate")
    ResponseEntity<?> activateTrainer(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {
            trainerService.activateTrainer(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>("Trainer activated", HttpStatus.OK);

        } catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping
    ResponseEntity<TrainerDto> updateTrainerProfile(@RequestBody @Valid UpdateTrainerProfileDto updateTrainerProfileDto){
        try{
            TrainerDto trainerDto = trainerService.updateTrainerProfile(updateTrainerProfileDto);

            return new ResponseEntity<>(trainerDto, HttpStatus.OK);

        }catch (WrongCredentialsException wce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(), wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @DeleteMapping
    ResponseEntity<?> deleteTrainerProfile(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {
            trainerService.deleteTrainer(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>("Trainer deleted", HttpStatus.OK);

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
