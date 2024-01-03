package com.epam.gymcrm.infrastructure.repositoryImpl;

import com.epam.gymcrm.domain.dao.TrainerDao;
import com.epam.gymcrm.domain.dto.TrainerDto;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TrainerRepositoryImpl implements TrainerRepository {

    private TrainerDao trainerDao;

    private ModelMapper modelMapper;

    public TrainerRepositoryImpl(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public TrainerDto createTrainer(TrainerDto trainerDto) {

        int id = trainerDao.getAll().size();
        trainerDto.setTrainerId(id);

        Trainer trainerToCreate = modelMapper.map(trainerDto, Trainer.class);
        trainerToCreate.setUserId(trainerDto.getUserDto().getId());

        Trainer trainerCreated = trainerDao.createTrainer(trainerToCreate);

        TrainerDto trainerDtoCreated  = modelMapper.map(trainerCreated, TrainerDto.class);


        log.debug("Trainer with id: " + trainerDtoCreated.getTrainerId() + " has been created.");

        return trainerDtoCreated;

    }

    @Override
    public List<Trainer> getAll(){
        return trainerDao.getAll();
    }

    @Override
    public Trainer get(int id){
        return trainerDao.get(id);
    }

    @Override
    public void updateTrainer(){

    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
