package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TrainerService;
import com.epam.gymcrm.application.UserService;
import com.epam.gymcrm.application.dto.trainee.TraineeDto;
import com.epam.gymcrm.application.dto.trainer.TrainerDto;
import com.epam.gymcrm.application.dto.trainer.TrainerTraineeListDTO;
import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeDto;
import com.epam.gymcrm.application.dto.UserDto;
import com.epam.gymcrm.application.dto.response.RegistrationResponseDTO;
import com.epam.gymcrm.application.dto.updateProfile.UpdateTrainerProfileDto;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.exceptions.WrongCredentialsException;
import com.epam.gymcrm.infrastructure.entity.TraineeEntity;
import com.epam.gymcrm.infrastructure.entity.TrainerEntity;
import com.epam.gymcrm.infrastructure.entity.UserEntity;
import com.epam.gymcrm.domain.repository.TrainerRepository;
import com.epam.gymcrm.domain.repository.TrainingTypeRepository;
import com.epam.gymcrm.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    private ModelMapper modelMapper;
    private final UserService userService;


    public TrainerServiceImpl(TrainerRepository trainerRepository, UserRepository userRepository, UserService userService, TrainingTypeRepository trainingTypeRepository) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @Override
    @Transactional
    public RegistrationResponseDTO createTrainerProfile(User userToCreate, Trainer trainerToCreate) {

        UserEntity userEntity = modelMapper.map(userToCreate, UserEntity.class);
        TrainerEntity trainerEntity = modelMapper.map(trainerToCreate, TrainerEntity.class);

        userEntity.setUsername(userService.calculateUsername(userToCreate.getFirstName(), userToCreate.getLastName()));
        userEntity.setPassword(userService.generatePassword());
        userEntity.setIsActive(true);

        UserEntity userEntityCreated = userRepository.save(userEntity);
        trainerEntity.setUser(userEntityCreated);

        trainerEntity.setSpecialization(trainingTypeRepository.findTrainingTypeByName(trainerToCreate.getSpecialization()));

        TrainerEntity trainerEntityCreated = trainerRepository.save(trainerEntity);

        log.debug("Trainer with trainerId: " + trainerEntityCreated.getTrainerId() +
                " and userId: " + trainerEntityCreated.getUser().getId() + " has been created.");



        return RegistrationResponseDTO.builder()
                .username(trainerEntityCreated.getUser().getUsername())
                .password(trainerEntityCreated.getUser().getPassword())
                .build();

    }


    @Override
    public TrainerDto trainerLogin(String username, String password) throws Exception{

            TrainerEntity trainerEntity =
                    trainerRepository.findTrainerByUserUsernameAndUserPassword(username, password);

            if (trainerEntity == null) {
                throw new WrongCredentialsException("Incorrect password submitted");
            }

            TrainerDto trainerDto = modelMapper.map(trainerEntity, TrainerDto.class);
            trainerDto.setUserDto(modelMapper.map(trainerEntity.getUser(), UserDto.class));

            return trainerDto;

    }

    @Override
    public TrainerDto getTrainer(String username) throws Exception{

        TrainerEntity trainerEntity =
                trainerRepository.findTrainerByUserUsername(username);

        if (trainerEntity == null) {
            log.error("Trainer with username " + username + "not found.");
            throw new Exception("Trainer not found");
        }

        TrainerDto trainerDto = modelMapper.map(trainerEntity, TrainerDto.class);
        trainerDto.setUserDto(modelMapper.map(trainerEntity.getUser(), UserDto.class));
        trainerDto.setSpecialization(modelMapper.map(trainerEntity.getSpecialization(), TrainingTypeDto.class));
        trainerDto.setTrainees(new ArrayList<TrainerTraineeListDTO>(
                trainerEntity.getTrainees().stream().map(traineeEntity -> TrainerTraineeListDTO.builder()
                        .username(traineeEntity.getUser().getUsername())
                        .firstName(trainerEntity.getUser().getFirstName())
                        .lastName(traineeEntity.getUser().getLastName())
                        .build())
                        .collect(Collectors.toList())));

        return trainerDto;

    }


    @Override
    public List<TraineeDto> getTraineeList(String username, String password) throws  Exception{
        trainerLogin(username, password);

        List<TraineeEntity> traineeList = trainerRepository.findTrainerByUserUsername(username).getTrainees();

        List<TraineeDto> traineeDtos = new ArrayList<>();

        for(TraineeEntity traineeEntity : traineeList){
            traineeDtos.add(modelMapper.map(traineeEntity, TraineeDto.class));
        }

        return traineeDtos;

    }



    @Override
    @Transactional
    public void changePassword(String username, String password, String newPassword) throws Exception{
        trainerLogin(username, password);

        int i = userRepository.updatePassword(username, newPassword);

        if(i==0){
            throw new Exception("Trainer password could not be changed.");
        }

    }


    @Override
    @Transactional
    public void deactivateTrainer(String username, String password) throws Exception{
        trainerLogin(username, password);

        int i = userRepository.deactivateUser(username);

        if(i==0){
            throw new Exception("Trainer could not be deactivated.");
        }

    }


    @Override
    @Transactional
    public void activateTrainer(String username, String password) throws Exception{
        trainerLogin(username, password);

        int i = userRepository.activateUser(username);

        if(i==0){
            throw new Exception("Trainer could not be activated.");
        }

    }


    @Override
    @Transactional
    public TrainerDto updateTrainerProfile(UpdateTrainerProfileDto updateTrainerProfileDto) throws Exception{
        trainerLogin(updateTrainerProfileDto.getUsername(), updateTrainerProfileDto.getPassword());

        TrainerEntity trainerEntity = trainerRepository.findTrainerByUserUsername(updateTrainerProfileDto.getUsername());
        trainerEntity.setSpecialization(trainingTypeRepository.findTrainingTypeByName(updateTrainerProfileDto.getSpecialization()));
        trainerEntity.getUser().setFirstName(updateTrainerProfileDto.getFirstName());
        trainerEntity.getUser().setLastName(updateTrainerProfileDto.getLastName());


        trainerRepository.save(trainerEntity);

        TrainerDto trainerDto = modelMapper.map(trainerEntity, TrainerDto.class);
        trainerDto.setUserDto(modelMapper.map(trainerEntity.getUser(), UserDto.class));
        trainerDto.setSpecialization(modelMapper.map(trainerEntity.getSpecialization(), TrainingTypeDto.class));

        return trainerDto;


    }


    @Override
    @Transactional
    public void deleteTrainer(String username, String password) throws Exception{
        trainerLogin(username, password);

        TrainerEntity trainerEntity = trainerRepository.findTrainerByUserUsername(username);
        trainerRepository.delete(trainerEntity);

    }




    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



}
