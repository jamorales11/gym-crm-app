package com.epam.gymcrm.application;

import com.epam.gymcrm.adapters.dto.CreateTrainerProfileDto;
import com.epam.gymcrm.adapters.dto.TrainerDto;

public interface TrainerFacade {


    TrainerDto createTrainerProfile(CreateTrainerProfileDto createTrainerProfileDto);


}
