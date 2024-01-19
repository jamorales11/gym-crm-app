package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TraineeService;
import com.epam.gymcrm.application.UserService;
import com.epam.gymcrm.domain.dto.TraineeDto;
import com.epam.gymcrm.domain.dto.TrainerDto;
import com.epam.gymcrm.domain.dto.TrainingTypeDto;
import com.epam.gymcrm.domain.dto.UserDto;
import com.epam.gymcrm.domain.dto.updateProfile.UpdateTraineeProfileDto;
import com.epam.gymcrm.domain.dto.updateProfile.UpdateTrainerProfileDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.exceptions.WrongCredentialsException;
import com.epam.gymcrm.infrastructure.entity.TraineeEntity;
import com.epam.gymcrm.infrastructure.entity.TrainerEntity;
import com.epam.gymcrm.infrastructure.entity.UserEntity;
import com.epam.gymcrm.infrastructure.repository.TraineeRepository;
import com.epam.gymcrm.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private UserService userService;


    public TraineeServiceImpl(TraineeRepository traineeRepository, UserRepository userRepository, UserService userService) {
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public TraineeDto createTraineeProfile(User userToCreate, Trainee traineeToCreate) {

        UserEntity userEntity = modelMapper.map(userToCreate, UserEntity.class);
        TraineeEntity traineeEntity = modelMapper.map(traineeToCreate, TraineeEntity.class);

        userEntity.setUsername(userService.calculateUsername(userToCreate.getFirstName(), userToCreate.getLastName()));
        userEntity.setPassword(userService.generatePassword());
        userEntity.setIsActive(true);

        UserEntity userEntityCreated = userRepository.save(userEntity);
        traineeEntity.setUser(userEntityCreated);

        TraineeEntity traineeEntityCreated = traineeRepository.save(traineeEntity);

        log.debug("Trainee with traineeId: " + traineeEntityCreated.getTraineeId() +
                " and userId: " + traineeEntityCreated.getUser().getId() + " has been created.");

        TraineeDto traineeDto = modelMapper.map(traineeEntityCreated, TraineeDto.class);
        traineeDto.setUserDto(modelMapper.map(traineeEntityCreated.getUser(), UserDto.class));

        return traineeDto;
    }



    @Override
    public TraineeDto traineeLogin(String username, String password) throws WrongCredentialsException{

            TraineeEntity traineeEntity = traineeRepository.findTraineeByUserUsernameAndUserPassword(username,password);

            if(traineeEntity == null){
                throw new WrongCredentialsException("Incorrect password submitted");
            }

            TraineeDto traineeDto = modelMapper.map(traineeEntity, TraineeDto.class);
            traineeDto.setUserDto(modelMapper.map(traineeEntity.getUser(), UserDto.class));

            return traineeDto;

    }



    @Override
    public TraineeDto getTrainee(String username, String password) throws Exception {
        traineeLogin(username, password);


        TraineeEntity traineeEntity = traineeRepository.findTraineeByUserUsername(username);

        if(traineeEntity == null){
            throw new Exception("Trainee not found");
        }

        TraineeDto traineeDto = modelMapper.map(traineeEntity, TraineeDto.class);
        traineeDto.setUserDto(modelMapper.map(traineeEntity.getUser(), UserDto.class));

        return traineeDto;

    }

    @Override
    public List<TrainerDto> getTrainerList(String username, String password) throws  Exception{
        traineeLogin(username,password);

        List<TrainerEntity> trainerList = traineeRepository.findTraineeByUserUsername(username).getTrainers();

        List<TrainerDto> trainerDtos = new ArrayList<>();

        for(TrainerEntity trainerEntity: trainerList){
            trainerDtos.add(modelMapper.map(trainerEntity, TrainerDto.class));
        }

        return trainerDtos;
    }






    @Override
    @Transactional
    public void changePassword(String username, String password, String newPassword) throws Exception{

        TraineeDto traineeDto = traineeLogin(username, password);

        int i = userRepository.updatePassword(username, newPassword);

        if(i==0){
            throw new Exception("Trainer password could not be changed.");
        }

    }


    @Override
    @Transactional
    public void deactivateTrainee(String username, String password) throws Exception{
        traineeLogin(username, password);

        int i = userRepository.deactivateUser(username);

        if(i==0){
            throw new Exception("Trainee could not be deactivated");
        }
    }


    @Override
    @Transactional
    public void activateTrainee(String username, String password) throws Exception{
        traineeLogin(username,password);

        int i = userRepository.activateUser(username);

        if(i==0){
            throw new Exception("Trainee could not be activated");
        }
    }

    @Override
    @Transactional
    public TraineeDto updateTraineeProfile(UpdateTraineeProfileDto updateTraineeProfileDto) throws Exception{
        traineeLogin(updateTraineeProfileDto.getUsername(), updateTraineeProfileDto.getPassword());

        TraineeEntity traineeEntity = traineeRepository.findTraineeByUserUsername(updateTraineeProfileDto.getUsername());
        traineeEntity.setAddress(updateTraineeProfileDto.getAddress());
        traineeEntity.setDateOfBirth(updateTraineeProfileDto.getDateOfBirth());
        traineeEntity.getUser().setFirstName(updateTraineeProfileDto.getFirstName());
        traineeEntity.getUser().setLastName(updateTraineeProfileDto.getLastName());


        traineeRepository.save(traineeEntity);

        TraineeDto traineeDto = modelMapper.map(traineeEntity, TraineeDto.class);
        traineeDto.setUserDto(modelMapper.map(traineeEntity.getUser(), UserDto.class));

        return traineeDto;

    }

    @Override
    @Transactional
    public void deleteTrainee(String username, String password) throws Exception{
        traineeLogin(username, password);

        TraineeEntity traineeEntity = traineeRepository.findTraineeByUserUsername(username);
        traineeRepository.delete(traineeEntity);

    }






    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



}
