package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.infrastructure.entity.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Integer>, CustomTrainingRepository {


}
