package com.epam.gymcrm.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;


@AllArgsConstructor
@Getter
@Setter
public class Trainee {

    private Integer traineeId;

    private String dateOfBirth;

    private String address;

    private Integer userId;

    private ArrayList<Trainer> trainers;

}
