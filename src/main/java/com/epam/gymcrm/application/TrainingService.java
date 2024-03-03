package com.epam.gymcrm.application;


import com.epam.gymcrm.application.dto.training.*;

import java.util.List;

public interface TrainingService {

    void createTraining(CreateTrainingDto trainingDto) throws Exception;

    List<ResponseTraineeTrainingDto> getTraineeTrainings(RequestTraineeTrainingsDto requestTraineeTrainingsDto);

    List<ResponseTrainerTrainingDto> getTrainerTrainings(RequestTrainerTrainingsDto requestTrainerTrainingsDto);
}
