package com.epam.gymcrm.domain.model;

import lombok.*;

import java.util.ArrayList;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trainee {

    private Integer traineeId;

    private String dateOfBirth;

    private String address;

    private Integer userId;

    private ArrayList<Trainer> trainers;

}
