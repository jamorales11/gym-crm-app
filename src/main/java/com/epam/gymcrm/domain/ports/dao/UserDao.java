package com.epam.gymcrm.domain.ports.dao;

import com.epam.gymcrm.domain.model.User;

import java.util.List;

public interface UserDao {

    User get(int id);

    List<User> findUsersByFirstNameAndLastName(String firstName, String lastName);

    List<User> getAll();
    User createUser(User user);
}
