package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TraineeService;
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


    private ModelMapper modelMapper;


    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;


    public TraineeServiceImpl(TraineeRepository traineeRepository, UserRepository userRepository){
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
    }

    public TraineeDto createTraineeProfile(CreateTraineeProfileDto createTraineeProfileDto){

        User userToCreate = modelMapper.map(createTraineeProfileDto, User.class);
        Trainee traineeToCreate = modelMapper.map(createTraineeProfileDto, Trainee.class);
        traineeToCreate.setUserId(userToCreate.getId());


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


}
