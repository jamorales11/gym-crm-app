package com.epam.gymcrm.domain.model;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Training {

    private String name;
    private Date date;
    private Integer duration;

}
