package com.epam.gymcrm.domain.service;

import com.epam.gymcrm.adapters.daoImpl.TrainerDaoImpl;
import com.epam.gymcrm.domain.ports.dao.TrainerDao;
import com.epam.gymcrm.adapters.dto.TrainerDto;
import com.epam.gymcrm.domain.model.Trainer;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService{

    private final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private TrainerDao trainerDao;

    @Autowired
    private ModelMapper modelMapper;

    public TrainerDto createTrainer(TrainerDto trainerDto) {

        int id = trainerDao.getAll().size();
        trainerDto.setTrainerId(id);

        Trainer trainerToCreate = modelMapper.map(trainerDto, Trainer.class);
        trainerToCreate.setUserId(trainerDto.getUserDto().getId());

        Trainer trainerCreated = trainerDao.createTrainer(trainerToCreate);

        TrainerDto trainerDtoCreated  = modelMapper.map(trainerCreated, TrainerDto.class);


        logger.debug("Trainer with id: " + trainerDtoCreated.getTrainerId() + " has been created.");

        return trainerDtoCreated;

    }

    public List<Trainer> getAll(){
        return trainerDao.getAll();
    }

    public Trainer get(int id){
        return trainerDao.get(id);
    }

    public void updateTrainer(){

    }
}
