package com.epam.gymcrm.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="training", schema="public")
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int duration;

    @ManyToOne (fetch = FetchType.LAZY)
    private TraineeEntity trainee;

    @ManyToOne (fetch = FetchType.LAZY)
    private TrainerEntity trainer;

    @ManyToOne (fetch = FetchType.LAZY)
    private TrainingTypeEntity trainingType;

}
