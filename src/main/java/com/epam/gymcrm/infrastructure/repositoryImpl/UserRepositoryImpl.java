package com.epam.gymcrm.infrastructure.repositoryImpl;

import com.epam.gymcrm.domain.dao.UserDao;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    public UserDao userDao;


    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public User createUser(User user) {

        User userCreated = userDao.createUser(user);

        log.debug("User with id: " + userCreated.getId() + " and username: " + userCreated.getUsername() + " has been created.");

        return userCreated;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    public List<User> findUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userDao.findUsersByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void updateUser() {

    }

}
