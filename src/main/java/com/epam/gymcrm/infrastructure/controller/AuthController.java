package com.epam.gymcrm.infrastructure.controller;

import com.epam.gymcrm.application.TraineeService;
import com.epam.gymcrm.application.TrainerService;
import com.epam.gymcrm.application.dto.RequestLoginDto;
import com.epam.gymcrm.application.dto.response.RegistrationResponseDTO;
import com.epam.gymcrm.application.dto.trainee.CreateTraineeProfileDto;
import com.epam.gymcrm.application.dto.trainer.CreateTrainerProfileDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.repository.RoleRepository;
import com.epam.gymcrm.domain.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Auth", description = "the Auth Api")
@RestController
@RequestMapping(value = "/api/auth")
@Slf4j
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TraineeService traineeService;

    @Autowired
    private TrainerService trainerService;


    @Operation(summary = "Login", description = "Username and password matching authentication")
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody RequestLoginDto requestLoginDto ){

        try {


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestLoginDto.getUsername(), requestLoginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("User " + requestLoginDto.getUsername() + " logged in successfully");

            return new ResponseEntity<>("User logged in successfully!", HttpStatus.OK);


        } catch (RuntimeException usernameNotFoundException){
            log.error("Incorrect credentials submitted in attempted login.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, usernameNotFoundException.getMessage(),usernameNotFoundException);
        }



    }


    @Operation(summary = "Trainee Registration", description = "A")
    @PostMapping("/traineeSignUp")
    ResponseEntity<RegistrationResponseDTO> createTraineeProfile(@RequestBody @Valid CreateTraineeProfileDto createTraineeProfileDto) {
        try {
            User userToCreate = modelMapper.map(createTraineeProfileDto, User.class);
            Trainee traineeToCreate = modelMapper.map(createTraineeProfileDto, Trainee.class);

            RegistrationResponseDTO registrationResponseDTO = traineeService.createTraineeProfile(userToCreate, traineeToCreate);
            return new ResponseEntity<>(registrationResponseDTO, HttpStatus.CREATED);

        } catch (Exception e){
            log.info("Incorrect credentials submitted in attempted login.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainee Not Created", e);
        }

    }


    @Operation(summary = "Trainer Registration", description = "Create a new Trainer profile")
    @PostMapping("/trainerSignUp")
    ResponseEntity<RegistrationResponseDTO> createTrainerProfile(@RequestBody @Valid CreateTrainerProfileDto createTrainerProfileDto) {

        try {

            User userToCreate = modelMapper.map(createTrainerProfileDto, User.class);
            Trainer trainerToCreate = modelMapper.map(createTrainerProfileDto, Trainer.class);


            RegistrationResponseDTO registrationResponseDTO = trainerService.createTrainerProfile(userToCreate, trainerToCreate);
            return new ResponseEntity<>(registrationResponseDTO, HttpStatus.CREATED);

        } catch (Exception e) {
            log.info("Incorrect credentials submitted in attempted login.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer Not Created", e);
        }
    }
}
