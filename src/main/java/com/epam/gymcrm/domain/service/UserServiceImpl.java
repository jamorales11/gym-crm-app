package com.epam.gymcrm.domain.service;

import com.epam.gymcrm.adapters.daoImpl.TrainerDaoImpl;
import com.epam.gymcrm.adapters.dto.TrainerDto;
import com.epam.gymcrm.adapters.dto.UserDto;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.ports.dao.UserDao;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {

        int id = userDao.getAll().size();
        userDto.setId(id);

        //Calculating username and generating password for User.
        userDto.setUsername(calculateUsername(userDto.getFirstName(), userDto.getLastName()));
        userDto.setPassword(generatePassword());

        User userCreated = userDao.createUser(modelMapper.map(userDto, User.class));

        UserDto userDtoCreated  = modelMapper.map(userCreated, UserDto.class);

        logger.debug("User with id: " + userDtoCreated.getId() + " and username: " + userDtoCreated.getUsername() + " has been created.");

        return userDtoCreated;
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
}
