package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.UserService;
import com.epam.gymcrm.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String calculateUsername(String firstName, String lastName) {
        StringBuilder username = new StringBuilder(firstName + "." + lastName);
        int qtyUsers = userRepository.findUsersByFirstNameAndLastName(firstName, lastName).size();

        if (qtyUsers != 0) {
            username.append(qtyUsers);
        }

        return username.toString();

    }

    @Override
    public String generatePassword() {
        StringBuilder password = new StringBuilder();
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
                + "jklmnopqrstuvwxyz!@#$%&";
        for (int i = 0; i < 10; i++) {
            password.append(chars.charAt(new Random().nextInt(chars.length())));
        }

        return password.toString();
    }
}
