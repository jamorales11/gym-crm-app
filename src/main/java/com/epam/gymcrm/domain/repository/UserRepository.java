package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.domain.dto.UserDto;
import com.epam.gymcrm.domain.model.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user);

    List<User> getAll();

    UserDto get(int id);

    void updateUser();
}
