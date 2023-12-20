package com.epam.gymcrm.model;

import java.util.Date;


public class Training {

    private String name;
    private Date date;
    private Integer duration;

    public Training() {
    }

    public Training(String name, Date date, Integer duration) {
        this.name = name;
        this.date = date;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
