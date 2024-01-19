package com.epam.gymcrm.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="training_type", schema="public")
public class TrainingTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "trainingType")
    private List<TrainingEntity> trainings = new ArrayList<>();;

    @OneToMany(mappedBy = "specialization")
    private List<TrainerEntity> trainers = new ArrayList<>();;
}
