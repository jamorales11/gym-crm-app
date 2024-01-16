package com.epam.gymcrm.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(schema="public")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String specialization;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;


    @ManyToMany
    @JoinTable(name = "trainers_trainees",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "trainee_id"))
    private ArrayList<Trainee> trainees;

    @OneToMany(mappedBy = "trainer")
    private ArrayList<Training> trainings;

    @ManyToOne
    private TrainingType trainingType;


}
