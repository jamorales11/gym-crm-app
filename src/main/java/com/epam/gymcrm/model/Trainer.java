package com.epam.gymcrm.model;

public class Trainer {

    private Integer id;

    private String specialization;

    public Trainer() {
    }

    public Trainer(Integer id, String specialization) {
        this.id = id;
        this.specialization = specialization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
