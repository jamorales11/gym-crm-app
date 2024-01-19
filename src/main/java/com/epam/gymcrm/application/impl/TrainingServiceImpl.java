package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TrainingService;
import com.epam.gymcrm.infrastructure.repository.TraineeRepository;
import com.epam.gymcrm.infrastructure.repository.TrainerRepository;
import com.epam.gymcrm.infrastructure.repository.TrainingTypeRepository;
import com.epam.gymcrm.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TrainingServiceImpl implements TrainingService {

    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    private ModelMapper modelMapper;

    public TrainingServiceImpl(TrainerRepository trainerRepository, TraineeRepository traineeRepository, UserRepository userRepository, TrainingTypeRepository trainingTypeRepository) {
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
        this.trainingTypeRepository = trainingTypeRepository;
    }





















    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
