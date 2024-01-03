package com.epam.gymcrm.domain.model;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingType {

    private Integer trainingTypeId;

    private String name;

    private List<Training> trainings;

    private List<Trainer> trainers;

}
