package com.epam.gymcrm.infrastructure.repositoryImpl;

import com.epam.gymcrm.domain.dao.UserDao;
import com.epam.gymcrm.domain.dto.UserDto;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    public UserDao userDao;

    private ModelMapper modelMapper;

    public UserRepositoryImpl(UserDao userDao){
        this.userDao = userDao;
    }


    @Override
    public User createUser(User user) {

        int id = userDao.getAll().size();
        user.setId(id);

        //Calculating username and generating password for User.
        user.setUsername(calculateUsername(user.getFirstName(), user.getLastName()));
        user.setPassword(generatePassword());

        User userCreated = userDao.createUser(user);

        log.debug("User with id: " + userCreated.getId() + " and username: " + userCreated.getUsername() + " has been created.");

        return userCreated;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public UserDto get(int id) {
        return modelMapper.map(userDao.get(id), UserDto.class);
    }

    @Override
    public void updateUser() {

    }


    private String calculateUsername(String firstName, String lastName){
        StringBuilder username = new StringBuilder(firstName + "." + lastName);
        int qtyUsers = userDao.findUsersByFirstNameAndLastName( firstName, lastName).size();

        if(qtyUsers != 0){
            username.append(qtyUsers);
        }


        return username.toString();

    }

    private String generatePassword(){
        StringBuilder password = new StringBuilder();
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
                +"jklmnopqrstuvwxyz!@#$%&";
        for (int i = 0; i <= 10; i++){
            password.append(chars.charAt(new Random().nextInt(chars.length())));
        }

        return password.toString();
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
