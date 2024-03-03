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
@Table(name="trainer", schema="public")
public class TrainerEntity {

    @Id
    @GeneratedValue
    private int trainerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private TrainingTypeEntity specialization;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private UserEntity user;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "trainers_trainees",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "trainee_id"))
    private List<TraineeEntity> trainees = new ArrayList<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<TrainingEntity> trainings = new ArrayList<>();



}
