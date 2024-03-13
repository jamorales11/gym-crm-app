package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.TrainingTypeService;
import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeDto;
import com.epam.gymcrm.application.dto.trainingtype.TrainingTypeListDto;
import com.epam.gymcrm.domain.repository.TrainingTypeRepository;
import com.epam.gymcrm.infrastructure.entity.TrainingTypeEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private final TrainingTypeRepository trainingTypeRepository;

    private ModelMapper modelMapper;

    public TrainingTypeServiceImpl(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    public List<TrainingTypeListDto> getTrainingTypeList(){

        List<TrainingTypeListDto> trainingTypeListDtos = new ArrayList<>();

        List<TrainingTypeEntity> trainingTypeEntities = trainingTypeRepository.findAll();

        for(TrainingTypeEntity trainingTypeEntity : trainingTypeEntities){
            trainingTypeListDtos.add(modelMapper.map(trainingTypeEntity, TrainingTypeListDto.class));
        }

        log.info("TrainingType list has been fetched.");



        return trainingTypeListDtos;

    }


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
