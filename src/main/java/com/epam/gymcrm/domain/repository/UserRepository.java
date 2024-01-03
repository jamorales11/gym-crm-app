package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.domain.model.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user);

    List<User> getAll();

    User get(int id);

    List<User> findUsersByFirstNameAndLastName(String firstName, String lastName);

    void updateUser();
}
