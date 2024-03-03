package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserEntityServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void calculateUsernameTest(){

        List<User> userList = new ArrayList<>();

        when(userRepository.findUsersByFirstNameAndLastName(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userList);

        String username = userService.calculateUsername("FirstName", "LastName");

        Assertions.assertEquals("FirstName.LastName",username);

    }

    @Test
    public void calculateUsernameExistingFirstNameLastNameTest(){

        List<User> userList = new ArrayList<>(){{
            add(new User(0,"FirstName", "LastName", "FirstName.LastName","asdffghopl", true));
        }};

        when(userRepository.findUsersByFirstNameAndLastName(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userList);

        String username = userService.calculateUsername("FirstName", "LastName");

        Assertions.assertEquals("FirstName.LastName1",username);

    }


    @Test
    public void generatePassword(){

        String password = userService.generatePassword();

        Assertions.assertEquals(10, password.length());

        String password2 = userService.generatePassword();

        Assertions.assertEquals(10, password.length());

        Assertions.assertNotEquals(password,password2);
    }
}
