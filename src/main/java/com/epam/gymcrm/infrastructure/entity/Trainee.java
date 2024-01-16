package com.epam.gymcrm.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(schema="public")
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;


    @ManyToMany(mappedBy = "trainees")
    private ArrayList<Trainer> trainers;

    @OneToMany(mappedBy = "trainee")
    private ArrayList<Training> trainings;

}
