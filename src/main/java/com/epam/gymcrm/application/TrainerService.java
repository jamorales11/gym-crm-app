package com.epam.gymcrm.application;

import com.epam.gymcrm.domain.dto.CreateTrainerProfileDto;
import com.epam.gymcrm.domain.dto.TrainerDto;

public interface TrainerService {


    TrainerDto createTrainerProfile(CreateTrainerProfileDto createTrainerProfileDto);


}
