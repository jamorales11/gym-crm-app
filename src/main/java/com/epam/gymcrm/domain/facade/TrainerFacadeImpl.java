package com.epam.gymcrm.domain.facade;

import com.epam.gymcrm.adapters.dto.UserDto;
import com.epam.gymcrm.domain.service.TrainerService;
import com.epam.gymcrm.adapters.dto.CreateTrainerProfileDto;
import com.epam.gymcrm.adapters.dto.TrainerDto;
import com.epam.gymcrm.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TrainerFacadeImpl implements TrainerFacade {

    private final Logger logger = LoggerFactory.getLogger(TrainerFacadeImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    private final TrainerService trainerService;
    private final UserService userService;


    public TrainerFacadeImpl(TrainerService trainerService, UserService userService){
        this.trainerService = trainerService;
        this.userService = userService;
    }

    public TrainerDto createTrainerProfile(CreateTrainerProfileDto createTrainerProfileDto){

        UserDto userDtoToCreate = modelMapper.map(createTrainerProfileDto, UserDto.class);
        TrainerDto trainerDtoToCreate = modelMapper.map(createTrainerProfileDto, TrainerDto.class);


        UserDto userDtoCreated = userService.createUser(userDtoToCreate);
        trainerDtoToCreate.setUserDto(userDtoCreated);

        TrainerDto trainerDtoCreated = trainerService.createTrainer(trainerDtoToCreate);


        trainerDtoCreated.setUserDto(userService.get(trainerDtoToCreate.getUserDto().getId()));

        logger.debug("Trainer with id: " + trainerDtoCreated.getTrainerId() + " and username: " + trainerDtoCreated.getUserDto().getUsername() +" has been created.");

        return trainerDtoCreated;
    }


}
