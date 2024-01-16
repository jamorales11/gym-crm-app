package com.epam.gymcrm.config.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class Storage {

    Map<String, Map<Integer, Object>> storage;


    public Storage() {

        this.storage = new HashMap<String, Map<Integer, Object>>() {
            {
                HashMap<Integer, Object> trainers = new HashMap();
                HashMap<Integer, Object> trainees = new HashMap();
                HashMap<Integer, Object> trainings = new HashMap();
                HashMap<Integer, Object> users = new HashMap();
                putAll(Map.of("Trainer", trainers,
                        "Trainee", trainees,
                        "Training", trainings,
                        "User", users));
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
