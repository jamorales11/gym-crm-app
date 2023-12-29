package com.epam.gymcrm.domain.facade;

import com.epam.gymcrm.adapters.dto.CreateTraineeProfileDto;
import com.epam.gymcrm.adapters.dto.CreateTrainerProfileDto;
import com.epam.gymcrm.adapters.dto.TraineeDto;
import com.epam.gymcrm.adapters.dto.TrainerDto;

public interface TraineeFacade {


    TraineeDto createTraineeProfile(CreateTraineeProfileDto createTraineeProfileDto);


}
