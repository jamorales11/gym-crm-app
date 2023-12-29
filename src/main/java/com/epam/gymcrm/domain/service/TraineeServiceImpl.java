package com.epam.gymcrm.domain.service;

import com.epam.gymcrm.adapters.dto.TraineeDto;
import com.epam.gymcrm.adapters.dto.TrainerDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.ports.dao.TraineeDao;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {

    private final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    public TraineeDao traineeDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TraineeDto createTrainee(TraineeDto traineeDto) {
        int id = traineeDao.getAll().size();
        traineeDto.setTraineeId(id);

        Trainee traineeToCreate = modelMapper.map(traineeDto, Trainee.class);
        traineeToCreate.setUserId(traineeDto.getUserDto().getId());

        Trainee traineeCreated = traineeDao.createTrainer(traineeToCreate);

        TraineeDto traineeDtoCreated  = modelMapper.map(traineeCreated, TraineeDto.class);


        logger.debug("Trainee with id: " + traineeDtoCreated.getTraineeId() + " has been created.");

        return traineeDtoCreated;
    }

    @Override
    public List<Trainee> getAll() {
        return traineeDao.getAll();
    }

    @Override
    public Trainee get(int id) {
        return traineeDao.get(id);
    }

    @Override
    public void updateTrainer() {

    }
}
