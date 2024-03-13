package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TrainingService;
import com.epam.gymcrm.application.dto.training.*;
import com.epam.gymcrm.domain.repository.TraineeRepository;
import com.epam.gymcrm.domain.repository.TrainerRepository;
import com.epam.gymcrm.domain.repository.TrainingRepository;
import com.epam.gymcrm.domain.repository.TrainingTypeRepository;
import com.epam.gymcrm.infrastructure.entity.TraineeEntity;
import com.epam.gymcrm.infrastructure.entity.TrainerEntity;
import com.epam.gymcrm.infrastructure.entity.TrainingEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TrainingServiceImpl implements TrainingService {


    private final TrainerRepository trainerRepository;

    private final TraineeRepository traineeRepository;

    private final TrainingTypeRepository trainingTypeRepository;

    private final TrainingRepository trainingRepository;

    private ModelMapper modelMapper;


    public TrainingServiceImpl(TrainerRepository trainerRepository, TraineeRepository traineeRepository, TrainingTypeRepository trainingTypeRepository, TrainingRepository trainingRepository) {
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingRepository = trainingRepository;
    }

    @Override
    @Transactional
    public void createTraining(CreateTrainingDto trainingDto) throws Exception{

        TraineeEntity traineeEntity = traineeRepository.findTraineeByUserUsername(trainingDto.getTraineeUsername());

        TrainerEntity trainerEntity =  trainerRepository.findTrainerByUserUsername(trainingDto.getTrainerUsername());



        if(!trainerEntity.getTrainees().contains(traineeEntity)){
            trainerEntity.getTrainees().add(traineeEntity);
            trainerRepository.save(trainerEntity);

            log.info("Trainee " + traineeEntity.getUser().getUsername() + " added to trainer " + trainerEntity.getUser().getUsername()
                    + " trainee list successfully!");

        }



        TrainingEntity training = TrainingEntity.builder().name(trainingDto.getName()).date(trainingDto.getDate())
                .duration(trainingDto.getDuration())
                .trainee(traineeEntity)
                .trainer(trainerEntity)
                .trainingType(trainerEntity.getSpecialization())
                .build();


        trainingRepository.save(training);

        log.info("Training  " + training.getName() + " created successfully for trainee " + traineeEntity.getUser().getUsername()
                + " and trainer " + trainerEntity.getUser().getUsername());


    }


    @Override
    public List<ResponseTraineeTrainingDto> getTraineeTrainings(RequestTraineeTrainingsDto req){



        List<TrainingEntity> trainingEntities = trainingRepository.findTraineeTrainingsByUsernameAndCriteria(req);

        List<ResponseTraineeTrainingDto> responseTraineeTrainingDtos = new ArrayList<>();

        for(TrainingEntity trainingEntity : trainingEntities){
            ResponseTraineeTrainingDto responseTraineeTrainingDto = modelMapper.map(trainingEntity, ResponseTraineeTrainingDto.class);
            responseTraineeTrainingDto.setTrainerName(trainingEntity.getTrainer().getUser().getFirstName());
            responseTraineeTrainingDto.setTrainingType(trainingEntity.getTrainingType().getName());
            responseTraineeTrainingDtos.add(responseTraineeTrainingDto);


        }

        log.info("Trainee " + req.getUsername() + "'s training list fetched successfully!");




        return responseTraineeTrainingDtos;

    }


    @Override
    public List<ResponseTrainerTrainingDto> getTrainerTrainings(RequestTrainerTrainingsDto req){



        List<TrainingEntity> trainingEntities = trainingRepository.findTrainerTrainingsByUsernameAndCriteria(req);

        List<ResponseTrainerTrainingDto> responseTrainerTrainingDtos = new ArrayList<>();

        for(TrainingEntity trainingEntity : trainingEntities){
            ResponseTrainerTrainingDto responseTrainerTrainingDto = modelMapper.map(trainingEntity, ResponseTrainerTrainingDto.class);
            responseTrainerTrainingDto.setTraineeName(trainingEntity.getTrainee().getUser().getFirstName());
            responseTrainerTrainingDto.setTrainingType(trainingEntity.getTrainingType().getName());
            responseTrainerTrainingDtos.add(responseTrainerTrainingDto);


        }

        log.info("Trainer " + req.getUsername() + "'s training list fetched successfully!");




        return responseTrainerTrainingDtos;

    }








    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
