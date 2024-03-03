package com.epam.gymcrm.infrastructure.controller;

import com.epam.gymcrm.application.TrainerService;
import com.epam.gymcrm.application.dto.ChangePasswordDto;
import com.epam.gymcrm.application.dto.RequestLoginDto;
import com.epam.gymcrm.application.dto.trainee.TraineeDto;
import com.epam.gymcrm.application.dto.trainer.TrainerDto;
import com.epam.gymcrm.application.dto.trainer.CreateTrainerProfileDto;
import com.epam.gymcrm.application.dto.response.RegistrationResponseDTO;
import com.epam.gymcrm.application.dto.updateProfile.UpdateTrainerProfileDto;
import com.epam.gymcrm.domain.model.Trainer;
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

@Tag(name = "Trainer", description = "the Trainer Api")
@RestController
@RequestMapping(value = "/api/trainer")
public class TrainerController {

    private ModelMapper modelMapper;

    private TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Operation(summary = "Trainer Registration", description = "Create a new Trainer profile")
    @PostMapping
    ResponseEntity<RegistrationResponseDTO> createTrainerProfile(@RequestBody @Valid CreateTrainerProfileDto createTrainerProfileDto) {

        try {


            User userToCreate = modelMapper.map(createTrainerProfileDto, User.class);
            Trainer trainerToCreate = modelMapper.map(createTrainerProfileDto, Trainer.class);


            RegistrationResponseDTO registrationResponseDTO = trainerService.createTrainerProfile(userToCreate, trainerToCreate);
            return new ResponseEntity<>(registrationResponseDTO, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer Not Created", e);
        }
    }

    @Operation(summary = "Get Trainer Profile", description = "Select Trainer profile by username")
    @GetMapping
    ResponseEntity<TrainerDto> selectTrainer(@RequestBody RequestLoginDto requestLoginDto){
        try {
            trainerService.trainerLogin(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            TrainerDto trainerDto = trainerService.getTrainer(requestLoginDto.getUsername());

            return new ResponseEntity<>(trainerDto, HttpStatus.FOUND);

        } catch (WrongCredentialsException wce){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, wce.getMessage(),wce);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "Trainer Login", description = "Trainer username and password matching")
    @GetMapping("/login")
    ResponseEntity<TrainerDto> trainerLogin(@RequestBody @Valid RequestLoginDto requestLoginDto) {

        try {

            TrainerDto trainerDtoFound = trainerService.trainerLogin( requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>(trainerDtoFound, HttpStatus.OK);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }

    @Operation(summary = "Get Trainer", description = "A")
    @GetMapping("/trainees")
    ResponseEntity<List<TraineeDto>> getTraineeList(@RequestBody @Valid RequestLoginDto requestLoginDto){
        try {

            List<TraineeDto> traineeDtos = trainerService.getTraineeList(requestLoginDto.getUsername(), requestLoginDto.getPassword());

            return new ResponseEntity<>(traineeDtos, HttpStatus.FOUND);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }

    @Operation(summary = "Change Trainee Login", description = "A")
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

    @Operation(summary = "Deactivate Trainer", description = "A")
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

    @Operation(summary = "Activate Trainer", description = "A")
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

    @Operation(summary = "Update Trainer profile", description = "A")
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

    /*
    @Operation(summary = "A", description = "A")
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

     */


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


}
