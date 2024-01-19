package com.epam.gymcrm.application;

import com.epam.gymcrm.domain.dto.TraineeDto;
import com.epam.gymcrm.domain.dto.TrainerDto;
import com.epam.gymcrm.domain.dto.updateProfile.UpdateTraineeProfileDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.exceptions.WrongCredentialsException;

import java.util.List;

public interface TraineeService {


    TraineeDto createTraineeProfile(User userToCreate, Trainee traineeToCreate);

    TraineeDto traineeLogin(String username, String password) throws WrongCredentialsException;

    TraineeDto getTrainee(String username, String password) throws Exception;

    List<TrainerDto> getTrainerList(String username, String password) throws  Exception;

    void changePassword(String username, String password, String newPassword) throws Exception;

    void deactivateTrainee(String username, String password) throws Exception;

    void activateTrainee(String username, String password) throws Exception;

    TraineeDto updateTraineeProfile(UpdateTraineeProfileDto updateTraineeProfileDto) throws Exception;

    void deleteTrainee(String username, String password) throws Exception;
}
