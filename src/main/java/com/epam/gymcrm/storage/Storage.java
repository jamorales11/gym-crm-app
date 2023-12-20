package com.epam.gymcrm.storage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
public class Storage {

    Map<String, Map<Integer, Object>> storage;

    public Storage() {
        this.storage = new HashMap<String, Map<Integer, Object>>() {
            {
                HashMap<Integer, Object> trainers = new HashMap();
                HashMap<Integer, Object> trainees = new HashMap();
                HashMap<Integer, Object> trainings = new HashMap();
                putAll(Map.of("Trainer", trainers,
                            "Trainee", trainees));
            }
        };
    }

    public Map<String, Map<Integer, Object>> getStorage() {
        return storage;
    }

    public void setStorage(Map<String, Map<Integer, Object>> storage) {
        this.storage = storage;
    }
}
