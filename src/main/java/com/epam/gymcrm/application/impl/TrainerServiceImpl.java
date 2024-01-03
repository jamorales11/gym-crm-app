package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TrainerService;
import com.epam.gymcrm.domain.dto.CreateTrainerProfileDto;
import com.epam.gymcrm.domain.dto.TrainerDto;
import com.epam.gymcrm.domain.dto.UserDto;
import com.epam.gymcrm.domain.repository.TrainerRepository;
import com.epam.gymcrm.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class TrainerServiceImpl implements TrainerService {

    private ModelMapper modelMapper;

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;


    public TrainerServiceImpl(TrainerRepository trainerRepository, UserRepository userRepository){
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
    }

    public TrainerDto createTrainerProfile(CreateTrainerProfileDto createTrainerProfileDto){


        UserDto userDtoToCreate = modelMapper.map(createTrainerProfileDto, UserDto.class);
        TrainerDto trainerDtoToCreate = modelMapper.map(createTrainerProfileDto, TrainerDto.class);


        //UserDto userDtoCreated = userService.createUser(userDtoToCreate);
        //trainerDtoToCreate.setUserDto(userDtoCreated);

        TrainerDto trainerDtoCreated = trainerRepository.createTrainer(trainerDtoToCreate);


        //trainerDtoCreated.setUserDto(userService.get(trainerDtoToCreate.getUserDto().getId()));

        log.debug("Trainer with id: " + trainerDtoCreated.getTrainerId()  +" has been created.");

        return trainerDtoCreated;




    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


}
