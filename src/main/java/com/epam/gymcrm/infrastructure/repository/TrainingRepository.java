package com.epam.gymcrm.infrastructure.repository;

import com.epam.gymcrm.domain.model.Training;
import com.epam.gymcrm.infrastructure.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<TrainerEntity, Integer> {


}
