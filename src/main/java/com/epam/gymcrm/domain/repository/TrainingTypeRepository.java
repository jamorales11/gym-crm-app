package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.domain.model.Training;
import com.epam.gymcrm.infrastructure.entity.TrainerEntity;
import com.epam.gymcrm.infrastructure.entity.TrainingTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingTypeEntity, Integer> {

    TrainingTypeEntity findTrainingTypeByName(String name);
}
