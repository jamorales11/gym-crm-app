package com.epam.gymcrm.domain.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingTypeDto {

    private int id;

    private String name;
}
