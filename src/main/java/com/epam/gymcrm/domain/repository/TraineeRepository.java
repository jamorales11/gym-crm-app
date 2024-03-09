package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.infrastructure.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends JpaRepository<TraineeEntity, Integer> {

    TraineeEntity findTraineeByUserUsernameAndUserPassword(String username, String password);

    Boolean existsTraineeByUserUsername(String username);

    TraineeEntity findTraineeByUserUsername(String username);





}
