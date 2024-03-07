package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TraineeService;
import com.epam.gymcrm.application.UserService;
import com.epam.gymcrm.application.dto.trainee.TraineeDto;
import com.epam.gymcrm.application.dto.trainee.TraineeTrainerListDTO;
import com.epam.gymcrm.application.dto.trainer.TrainerDto;
import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeDto;
import com.epam.gymcrm.application.dto.UserDto;
import com.epam.gymcrm.application.dto.response.RegistrationResponseDTO;
import com.epam.gymcrm.application.dto.updateProfile.UpdateTraineeProfileDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.exceptions.WrongCredentialsException;
import com.epam.gymcrm.infrastructure.entity.TraineeEntity;
import com.epam.gymcrm.infrastructure.entity.TrainerEntity;
import com.epam.gymcrm.infrastructure.entity.UserEntity;
import com.epam.gymcrm.domain.repository.TraineeRepository;
import com.epam.gymcrm.domain.repository.TrainerRepository;
import com.epam.gymcrm.domain.repository.UserRepository;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
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
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;

    private final TrainerRepository trainerRepository;


    private ModelMapper modelMapper;
    private UserService userService;

    private Counter newTraineesCounter;

    private Counter deactivateTraineeCounter;



    public TraineeServiceImpl(TraineeRepository traineeRepository, UserRepository userRepository, TrainerRepository trainerRepository, UserService userService, MeterRegistry registry) {
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
        this.userService = userService;

        //counter
        this.newTraineesCounter = Counter.builder("create_trainee_request_total").tag("version", "v1").description("Trainees Create Request Count").register(registry);
        this.deactivateTraineeCounter = Counter.builder("deactivate_trainee_request_total").tag("version", "v1").description("Deactivate Trainee Request Count").register(registry);

    }

    @Override
    @Transactional
    @Timed(value = "do.create.trainee.timed")
    public RegistrationResponseDTO createTraineeProfile(User userToCreate, Trainee traineeToCreate) {

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

        //Incrementing counter metric
        newTraineesCounter.increment();


        return RegistrationResponseDTO.builder()
                .username(traineeEntityCreated.getUser().getUsername())
                .password(traineeEntityCreated.getUser().getPassword())
                .build();
    }



    @Override
    public TraineeDto traineeLogin(String username, String password) throws Exception{

            TraineeEntity traineeEntity = traineeRepository.findTraineeByUserUsernameAndUserPassword(username,password);

            if(traineeEntity == null){
                throw new WrongCredentialsException("Incorrect password submitted");
            }

            TraineeDto traineeDto = modelMapper.map(traineeEntity, TraineeDto.class);
            traineeDto.setUserDto(modelMapper.map(traineeEntity.getUser(), UserDto.class));

            return traineeDto;

    }



    @Override
    public TraineeDto getTrainee(String username) throws Exception {

        TraineeEntity traineeEntity = traineeRepository.findTraineeByUserUsername(username);

        if(traineeEntity == null){
            log.error("Trainee with username " + username + "not found.");
            throw new Exception("Trainee not found");
        }

        TraineeDto traineeDto = modelMapper.map(traineeEntity, TraineeDto.class);
        traineeDto.setUserDto(modelMapper.map(traineeEntity.getUser(), UserDto.class));
        traineeDto.setTrainers(new ArrayList<TraineeTrainerListDTO>(
                traineeEntity.getTrainers().stream().map(trainerEntity -> TraineeTrainerListDTO.builder()
                                .username(trainerEntity.getUser().getUsername())
                                .firstName(trainerEntity.getUser().getFirstName())
                                .lastName(trainerEntity.getUser().getLastName())
                                .specialization(modelMapper.map(trainerEntity.getSpecialization(), TrainingTypeDto.class))
                                .build())
                        .collect(Collectors.toList())));

        return traineeDto;

    }





    @Override
    public List<TrainerDto> getTrainerList(String username, String password) throws  Exception{
        traineeLogin(username,password);

        List<TrainerEntity> trainerList = traineeRepository.findTraineeByUserUsername(username).getTrainers();

        List<TrainerDto> trainerDtos = new ArrayList<>();

        for(TrainerEntity trainerEntity: trainerList){
            trainerDtos.add(modelMapper.map(trainerEntity, TrainerDto.class));
            trainerDtos.add(TrainerDto.builder().build());
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
        deactivateTraineeCounter.increment();
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





    @Override
    @Transactional
    public List<TraineeTrainerListDTO> getNotAssignedOnTrainers(String username, String password) throws Exception{

        traineeLogin(username,password);
        List<TrainerEntity> trainersRes = trainerRepository.findAll();

        List<TrainerEntity> trainersAssigned = traineeRepository.findTraineeByUserUsername(username)
                        .getTrainers();

        List<TrainerEntity> trainerEntityList = trainersRes.stream().filter(trainerEntity -> (!trainersAssigned.contains(trainerEntity))).toList();

        List<TraineeTrainerListDTO> trainerDtos = new ArrayList<>();

        for (TrainerEntity trainer : trainerEntityList){
            trainerDtos.add(TraineeTrainerListDTO.builder()
                            .username(trainer.getUser().getUsername())
                            .firstName(trainer.getUser().getFirstName())
                            .lastName(trainer.getUser().getLastName())
                            .specialization(modelMapper.map(trainer.getSpecialization(), TrainingTypeDto.class))
                    .build());
        }

        return trainerDtos;



    }






    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



}
