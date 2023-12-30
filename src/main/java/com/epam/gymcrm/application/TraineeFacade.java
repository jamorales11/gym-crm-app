package com.epam.gymcrm.application;

import com.epam.gymcrm.adapters.dto.CreateTraineeProfileDto;
import com.epam.gymcrm.adapters.dto.CreateTrainerProfileDto;
import com.epam.gymcrm.adapters.dto.TraineeDto;
import com.epam.gymcrm.adapters.dto.TrainerDto;

public interface TraineeFacade {


    TraineeDto createTraineeProfile(CreateTraineeProfileDto createTraineeProfileDto);


}
