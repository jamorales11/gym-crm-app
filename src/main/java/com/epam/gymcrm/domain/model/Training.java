package com.epam.gymcrm.domain.model;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Training {

    private Integer trainingId;

    private Integer traineeId;

    private Integer trainerId;

    private String name;

    private Integer trainingTypeId;

    private Date date;

    private Integer duration;

}
