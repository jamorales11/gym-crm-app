package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.domain.dto.CreateTraineeProfileDto;
import com.epam.gymcrm.domain.dto.TraineeDto;
import com.epam.gymcrm.domain.dto.UserDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.infrastructure.repositoryImpl.TraineeRepositoryImpl;
import com.epam.gymcrm.infrastructure.repositoryImpl.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

//ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TraineeServiceTest {

    /*
    @Spy
    ModelMapper modelMapper;


     */

    @Mock
    TraineeRepositoryImpl traineeRepository;

    @Mock
    UserRepositoryImpl userRepository;

    @InjectMocks
    TraineeServiceImpl traineeService;

    @BeforeEach
    void setUp(){
        traineeService.setModelMapper(new ModelMapper());
    }

    @Test
    public void createTraineeProfileTest(){

        Trainee trainee = Trainee.builder().traineeId(1).dateOfBirth("A").address("A").userId(1).trainers(new ArrayList<>())
                .build();

        User user = User.builder().id(1).firstName("A").lastName("A").username("A").password("A").isActive(true)
                .build();

        TraineeDto traineeDto = TraineeDto.builder().traineeId(1).dateOfBirth("A")
                .address("A").userDto(new UserDto()).trainers(new ArrayList<>())
                .build();


        when(traineeRepository.createTrainee(Mockito.any(Trainee.class))).thenReturn(trainee);
        when(userRepository.createUser(Mockito.any(User.class))).thenReturn(user);



        CreateTraineeProfileDto createTraineeProfileDto = new CreateTraineeProfileDto(1,"A","A",true,"A","a");

        TraineeDto traineeCreated = traineeService.createTraineeProfile(createTraineeProfileDto);

        Assertions.assertNotNull(traineeCreated);
        Assertions.assertNotNull(traineeCreated.getUserDto());
        Assertions.assertEquals(user.getId(), traineeCreated.getUserDto().getId());
    }

}
