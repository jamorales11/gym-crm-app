package com.epam.gymcrm.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(schema="public")
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "trainingType")
    private List<Training> trainings;

    @OneToMany(mappedBy = "trainingType")
    private List<Trainer> trainers;
}
