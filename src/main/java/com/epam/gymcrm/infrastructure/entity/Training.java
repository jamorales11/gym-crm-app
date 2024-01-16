package com.epam.gymcrm.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(schema="public")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int duration;

    @ManyToOne
    private Trainee trainee;

    @ManyToOne
    private Trainer trainer;

    @ManyToOne
    private TrainingType trainingType;

}
