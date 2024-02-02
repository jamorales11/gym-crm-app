package com.epam.gymcrm.application;

import com.epam.gymcrm.application.dto.trainee.TraineeDto;
import com.epam.gymcrm.application.dto.trainee.TraineeTrainerListDTO;
import com.epam.gymcrm.application.dto.trainer.TrainerDto;
import com.epam.gymcrm.application.dto.response.RegistrationResponseDTO;
import com.epam.gymcrm.application.dto.updateProfile.UpdateTraineeProfileDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.User;

import java.util.List;

public interface TraineeService {


    RegistrationResponseDTO createTraineeProfile(User userToCreate, Trainee traineeToCreate);

    TraineeDto traineeLogin(String username, String password) throws Exception;

    TraineeDto getTrainee(String username) throws Exception;


    List<TrainerDto> getTrainerList(String username, String password) throws  Exception;

    void changePassword(String username, String password, String newPassword) throws Exception;

    void deactivateTrainee(String username, String password) throws Exception;

    void activateTrainee(String username, String password) throws Exception;

    TraineeDto updateTraineeProfile(UpdateTraineeProfileDto updateTraineeProfileDto) throws Exception;

    void deleteTrainee(String username, String password) throws Exception;

    List<TraineeTrainerListDTO> getNotAssignedOnTrainers(String username, String password) throws Exception;;
}
