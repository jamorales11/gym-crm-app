package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.UserService;
import com.epam.gymcrm.domain.dto.*;
import com.epam.gymcrm.domain.dto.createProfile.CreateTrainerProfileDto;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.infrastructure.entity.UserEntity;
import com.epam.gymcrm.infrastructure.repository.TrainerRepository;
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

import static org.mockito.Mockito.when;

//ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TrainerEntityServiceTest {

    @Mock
    TrainerRepository trainerRepository;

    @Mock
    UserRepository userRepository;

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

        UserEntity userEntity = UserEntity.builder().id(1).firstName("A").lastName("A").username("A").password("A").isActive(true)
                .build();

        TrainerDto trainerDto = TrainerDto.builder().trainerId(1).specialization("spe").userDto(new UserDto()).trainees(new ArrayList<>())
                .build();

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
        when(trainerRepository.createTrainer(Mockito.any(Trainer.class))).thenReturn(trainer);

        CreateTrainerProfileDto createTrainerProfileDto = new CreateTrainerProfileDto("A","A","A");

        TrainerDto trainerCreated = trainerService.createTrainerProfile(createTrainerProfileDto);

        Assertions.assertNotNull(trainerCreated);
        Assertions.assertNotNull(trainerCreated.getUserDto());
        Assertions.assertEquals(userEntity.getId(), trainerCreated.getUserDto().getId());
    }

}
