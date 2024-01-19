package com.epam.gymcrm.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trainee", schema="public")
public class TraineeEntity {

    @Id
    @GeneratedValue
    private int traineeId;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private UserEntity user;

    @ManyToMany(mappedBy = "trainees")
    private List<TrainerEntity> trainers = new ArrayList<>();;

    @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL)
    private List<TrainingEntity> trainings = new ArrayList<>();;

}
