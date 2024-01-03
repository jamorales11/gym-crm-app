package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TrainerService;
import com.epam.gymcrm.application.UserService;
import com.epam.gymcrm.domain.dto.CreateTrainerProfileDto;
import com.epam.gymcrm.domain.dto.TrainerDto;
import com.epam.gymcrm.domain.dto.UserDto;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.repository.TrainerRepository;
import com.epam.gymcrm.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private UserService userService;


    public TrainerServiceImpl(TrainerRepository trainerRepository, UserRepository userRepository) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TrainerDto createTrainerProfile(CreateTrainerProfileDto createTrainerProfileDto) {

        User userToCreate = modelMapper.map(createTrainerProfileDto, User.class);
        Trainer trainerToCreate = modelMapper.map(createTrainerProfileDto, Trainer.class);

        trainerToCreate.setTrainerId(trainerRepository.getAll().size());

        userToCreate.setId(userRepository.getAll().size());
        trainerToCreate.setUserId(userToCreate.getId());

        //Calculating username and generating password for User.
        userToCreate.setUsername(userService.calculateUsername(userToCreate.getFirstName(), userToCreate.getLastName()));
        userToCreate.setPassword(userService.generatePassword());

        userToCreate.setIsActive(true);

        User userCreated = userRepository.createUser(userToCreate);
        Trainer trainerCreated = trainerRepository.createTrainer(trainerToCreate);

        TrainerDto trainerDto = modelMapper.map(trainerCreated, TrainerDto.class);
        trainerDto.setUserDto(modelMapper.map(userCreated, UserDto.class));

        log.debug("Trainee with traineeId: " + trainerCreated.getTrainerId() + " and userId: " + trainerCreated.getUserId() + " has been created.");

        return trainerDto;

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
