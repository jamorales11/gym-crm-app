package com.epam.gymcrm.application;

import com.epam.gymcrm.domain.dto.CreateTraineeProfileDto;
import com.epam.gymcrm.domain.dto.TraineeDto;

public interface TraineeService {


    TraineeDto createTraineeProfile(CreateTraineeProfileDto createTraineeProfileDto);


}
