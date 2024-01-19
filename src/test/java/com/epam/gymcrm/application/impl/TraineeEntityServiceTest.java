package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.UserService;
import com.epam.gymcrm.domain.dto.createProfile.CreateTraineeProfileDto;
import com.epam.gymcrm.domain.dto.TraineeDto;
import com.epam.gymcrm.domain.dto.UserDto;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.infrastructure.entity.UserEntity;
import com.epam.gymcrm.infrastructure.repository.TraineeRepository;
import com.epam.gymcrm.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

//ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TraineeEntityServiceTest {

    @Mock
    TraineeRepository traineeRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    @InjectMocks
    TraineeServiceImpl traineeService;

    @BeforeEach
    void setUp(){
        traineeService.setModelMapper(new ModelMapper());
        traineeService.setUserService(new UserServiceImpl(userRepository));
    }

    @Test
    public void createTraineeProfileTest(){

        Trainee trainee = Trainee.builder().traineeId(1).dateOfBirth("A").address("A").userId(1).trainers(new ArrayList<>())
                .build();

        UserEntity userEntity = UserEntity.builder().id(1).firstName("A").lastName("A").username("A").password("A").isActive(true)
                .build();

        TraineeDto traineeDto = TraineeDto.builder().traineeId(1).dateOfBirth("A")
                .address("A").userDto(new UserDto()).trainers(new ArrayList<>())
                .build();

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
        when(traineeRepository.createTrainee(Mockito.any(Trainee.class))).thenReturn(trainee);

        CreateTraineeProfileDto createTraineeProfileDto = new CreateTraineeProfileDto("A","A","A","a");

        TraineeDto traineeCreated = traineeService.createTraineeProfile(createTraineeProfileDto);

        Assertions.assertNotNull(traineeCreated);
        Assertions.assertNotNull(traineeCreated.getUserDto());
        Assertions.assertEquals(userEntity.getId(), traineeCreated.getUserDto().getId());
    }

}
