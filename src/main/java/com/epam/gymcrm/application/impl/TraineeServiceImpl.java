package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TraineeService;
import com.epam.gymcrm.application.UserService;
import com.epam.gymcrm.domain.dto.CreateTraineeProfileDto;
import com.epam.gymcrm.domain.dto.TraineeDto;
import com.epam.gymcrm.domain.dto.UserDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.repository.TraineeRepository;
import com.epam.gymcrm.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private UserService userService;


    public TraineeServiceImpl(TraineeRepository traineeRepository, UserRepository userRepository) {
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TraineeDto createTraineeProfile(CreateTraineeProfileDto createTraineeProfileDto) {

        User userToCreate = modelMapper.map(createTraineeProfileDto, User.class);
        Trainee traineeToCreate = modelMapper.map(createTraineeProfileDto, Trainee.class);

        traineeToCreate.setTraineeId(traineeRepository.getAll().size());

        userToCreate.setId(userRepository.getAll().size());
        traineeToCreate.setUserId(userToCreate.getId());

        //Calculating username and generating password for User.
        userToCreate.setUsername(userService.calculateUsername(userToCreate.getFirstName(), userToCreate.getLastName()));
        userToCreate.setPassword(userService.generatePassword());

        userToCreate.setIsActive(true);

        User userCreated = userRepository.createUser(userToCreate);
        Trainee traineeCreated = traineeRepository.createTrainee(traineeToCreate);

        TraineeDto traineeDto = modelMapper.map(traineeCreated, TraineeDto.class);
        traineeDto.setUserDto(modelMapper.map(userCreated, UserDto.class));

        log.debug("Trainee with traineeId: " + traineeCreated.getTraineeId() + " and userId: " + traineeCreated.getUserId() + " has been created.");

        return traineeDto;
    }


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
