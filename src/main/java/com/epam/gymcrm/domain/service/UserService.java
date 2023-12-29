package com.epam.gymcrm.domain.service;

import com.epam.gymcrm.adapters.dto.TrainerDto;
import com.epam.gymcrm.adapters.dto.UserDto;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.model.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<User> getAll();

    UserDto get(int id);

    void updateUser();
}
