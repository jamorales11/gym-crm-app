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

    @Value("${storage.path}")
    private String storagePath;



    public Storage() {

        this.storage = new HashMap<String, Map<Integer, Object>>() {
            {
                HashMap<Integer, Object> trainers = new HashMap();
                HashMap<Integer, Object> trainees = new HashMap();
                HashMap<Integer, Object> trainings = new HashMap();
                HashMap<Integer, Object> users = new HashMap();
                putAll(Map.of("Trainer", trainers,
                        "Trainee", trainees,
                        "trainings", trainings,
                        "User", users));
            }
        };


        //System.out.println(storagePath);
        //this.storage = populateMap();

    }


    private Map<String, Map<Integer, Object>> populateMap(){

        Map<String, Map<Integer, Object>> storageData = new HashMap<>();

        // create instance of the ObjectMapper class to map JSON data
        ObjectMapper mapper = new ObjectMapper();
        // create instance of the File class

        File fileObj = new File(storagePath);

        // use try-catch block to convert JSON data into Map
        try {
            // read JSON data from file using fileObj and map it using ObjectMapper and TypeReference classes
            storageData = mapper.readValue(
                    fileObj, new TypeReference<Map<String, Map<Integer, Object>>>() {
                    });
            // print all key-value pairs
            //System.out.println("Name : " + userData.get("Name"));



        } catch (Exception e) {
            // show error message
            e.printStackTrace();
        }
        return storageData;
    }

    public Map<String, Map<Integer, Object>> getStorage() {
        return storage;
    }

    public void setStorage(Map<String, Map<Integer, Object>> storage) {
        this.storage = storage;
    }

    /*
    @PostConstruct
    public void customInit(){
        System.out.println("Print environment values");
        System.out.println(storagePath);
    }

     */


}
