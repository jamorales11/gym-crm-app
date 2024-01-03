package com.epam.gymcrm.domain.model;

import lombok.*;

import java.util.ArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {

    private Integer trainerId;

    private String specialization;

    private Integer userId;

    private ArrayList<Trainee> trainees;


}
