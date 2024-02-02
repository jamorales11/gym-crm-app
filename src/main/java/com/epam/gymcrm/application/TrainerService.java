package com.epam.gymcrm.application;

import com.epam.gymcrm.application.dto.trainee.TraineeDto;
import com.epam.gymcrm.application.dto.trainer.TrainerDto;
import com.epam.gymcrm.application.dto.response.RegistrationResponseDTO;
import com.epam.gymcrm.application.dto.updateProfile.UpdateTrainerProfileDto;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.model.User;

import java.util.List;

public interface TrainerService {


    RegistrationResponseDTO createTrainerProfile(User userToCreate, Trainer trainerToCreate);

    TrainerDto trainerLogin(String username, String password) throws Exception;

    TrainerDto getTrainer(String username) throws Exception;

    List<TraineeDto> getTraineeList(String username, String password) throws  Exception;

    void changePassword(String username, String password,  String newPassword) throws Exception;

    void deactivateTrainer(String username, String password) throws Exception;

    void activateTrainer(String username, String password) throws Exception;

    TrainerDto updateTrainerProfile(UpdateTrainerProfileDto updateTrainerProfileDto) throws Exception;

    void deleteTrainer(String username, String password) throws Exception;


}
