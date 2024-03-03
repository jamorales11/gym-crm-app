package com.epam.gymcrm.domain.repository;


import com.epam.gymcrm.infrastructure.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Integer> {

    TrainerEntity findTrainerByUserUsernameAndUserPassword(String username, String password);

    TrainerEntity findTrainerByUserUsername(String username);

    //List<TrainerEntity> getAll();

    //TrainerEntity get(int id);

    //TrainerEntity updateTrainer(TrainerEntity trainerEntity);
}
