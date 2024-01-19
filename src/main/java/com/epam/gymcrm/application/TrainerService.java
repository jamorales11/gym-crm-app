package com.epam.gymcrm.application;

import com.epam.gymcrm.domain.dto.TraineeDto;
import com.epam.gymcrm.domain.dto.TrainerDto;
import com.epam.gymcrm.domain.dto.updateProfile.UpdateTrainerProfileDto;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public interface TrainerService {


    TrainerDto createTrainerProfile(User userToCreate, Trainer trainerToCreate);

    TrainerDto trainerLogin(String username, String password) throws Exception;

    TrainerDto getTrainer(String username, String password) throws Exception;

    List<TraineeDto> getTraineeList(String username, String password) throws  Exception;

    void changePassword(String username, String password,  String newPassword) throws Exception;

    void deactivateTrainer(String username, String password) throws Exception;

    void activateTrainer(String username, String password) throws Exception;

    TrainerDto updateTrainerProfile(UpdateTrainerProfileDto updateTrainerProfileDto) throws Exception;

    void deleteTrainer(String username, String password) throws Exception;


}
