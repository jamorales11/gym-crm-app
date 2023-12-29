package com.epam.gymcrm.domain.facade;

import com.epam.gymcrm.adapters.dto.*;
import com.epam.gymcrm.domain.service.TraineeService;
import com.epam.gymcrm.domain.service.TrainerService;
import com.epam.gymcrm.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeFacadeImpl implements TraineeFacade {

    private final Logger logger = LoggerFactory.getLogger(TraineeFacadeImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    private final TraineeService traineeService;
    private final UserService userService;


    public TraineeFacadeImpl(TraineeService traineeService, UserService userService){
        this.traineeService = traineeService;
        this.userService = userService;
    }

    public TraineeDto createTraineeProfile(CreateTraineeProfileDto createTraineeProfileDto){

        UserDto userDtoToCreate = modelMapper.map(createTraineeProfileDto, UserDto.class);
        TraineeDto traineeDtoToCreate = modelMapper.map(createTraineeProfileDto, TraineeDto.class);


        UserDto userDtoCreated = userService.createUser(userDtoToCreate);
        traineeDtoToCreate.setUserDto(userDtoCreated);

        TraineeDto traineeDtoCreated = traineeService.createTrainee(traineeDtoToCreate);


        traineeDtoCreated.setUserDto(userService.get(traineeDtoToCreate.getUserDto().getId()));

        logger.debug("Trainee with id: " + traineeDtoCreated.getTraineeId() + " and username: " + traineeDtoCreated.getUserDto().getUsername() +" has been created.");

        return traineeDtoCreated;
    }


}
