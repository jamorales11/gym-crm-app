package com.epam.gymcrm.infrastructure.repository;

import com.epam.gymcrm.infrastructure.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<TraineeEntity, Integer> {

    TraineeEntity findTraineeByUserUsernameAndUserPassword(String username, String password);

    TraineeEntity findTraineeByUserUsername(String username);


    //List<TrainerEntity> getAll();

    //TraineeEntity get(int id);

    //void updateTrainer();
}
