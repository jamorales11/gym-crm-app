package com.epam.gymcrm.domain.service;

import com.epam.gymcrm.adapters.dto.TrainerDto;
import com.epam.gymcrm.adapters.dto.UserDto;
import com.epam.gymcrm.domain.model.Trainer;
import com.epam.gymcrm.domain.model.User;
import com.epam.gymcrm.domain.ports.dao.TrainerDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceImplTest {

    @Mock
    private TrainerDao trainerDao;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TrainerServiceImpl trainerServiceImpl;

    @BeforeEach
    void setUp(){

        modelMapper = mock(ModelMapper.class);



    }

    @Test
    public void testCreateTrainer(){
        Trainer trainerToCreate = Trainer.builder().trainerId(1).specialization("a").userId(1).trainees(new ArrayList<>()).build();
        Trainer trainerCreated = Trainer.builder().trainerId(1).specialization("a").userId(1).trainees(new ArrayList<>()).build();

        UserDto userDto = UserDto.builder().firstName("Julio").lastName("Morales").id(1).username("Julio.Morales").password("sasdasdad").isActive(true)
                .build();
        TrainerDto trainerDto = TrainerDto.builder().trainerId(1).specialization("A").userDto(userDto).trainees(new ArrayList<>()).build();


        when(modelMapper.map(trainerDto,Trainer.class)).thenReturn(trainerToCreate);
        doNothing().when(trainerToCreate).setUserId(isA(Integer.class));

        when(trainerDao.createTrainer(Mockito.any(Trainer.class))).thenReturn(trainerCreated);

        UserDto userDto1 = UserDto.builder().firstName("Julio").lastName("Morales").id(1).username("Julio.Morales").password("sasdasdad").isActive(true)
                .build();
        TrainerDto trainerDto1 = TrainerDto.builder().trainerId(1).specialization("A").userDto(userDto1).trainees(new ArrayList<>()).build();

        TrainerDto trainerDtoCreated = trainerServiceImpl.createTrainer(trainerDto1);

        Assertions.assertNotNull(trainerDtoCreated);
    }


}
