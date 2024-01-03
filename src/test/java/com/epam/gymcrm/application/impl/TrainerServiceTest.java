package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.UserService;
import com.epam.gymcrm.domain.dto.*;
import com.epam.gymcrm.domain.model.Trainee;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.infrastructure.repositoryImpl.TrainerRepositoryImpl;
import com.epam.gymcrm.infrastructure.repositoryImpl.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

//ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TrainerServiceTest {

    @Mock
    TrainerRepositoryImpl trainerRepository;

    @Mock
    UserRepositoryImpl userRepository;

    @Mock
    UserService userService;

    @InjectMocks
    TrainerServiceImpl trainerService;

    @BeforeEach
    void setUp(){
        trainerService.setModelMapper(new ModelMapper());
        trainerService.setUserService(new UserServiceImpl(userRepository));
    }

    @Test
    public void createTrainerProfileTest(){

        Trainer trainer = Trainer.builder().trainerId(1).specialization("spe").userId(1).trainees(new ArrayList<>())
                .build();

        User user = User.builder().id(1).firstName("A").lastName("A").username("A").password("A").isActive(true)
                .build();

        TrainerDto trainerDto = TrainerDto.builder().trainerId(1).specialization("spe").userDto(new UserDto()).trainees(new ArrayList<>())
                .build();

        when(userRepository.createUser(Mockito.any(User.class))).thenReturn(user);
        when(trainerRepository.createTrainer(Mockito.any(Trainer.class))).thenReturn(trainer);

        CreateTrainerProfileDto createTrainerProfileDto = new CreateTrainerProfileDto("A","A","A");

        TrainerDto trainerCreated = trainerService.createTrainerProfile(createTrainerProfileDto);

        Assertions.assertNotNull(trainerCreated);
        Assertions.assertNotNull(trainerCreated.getUserDto());
        Assertions.assertEquals(user.getId(), trainerCreated.getUserDto().getId());
    }

}
