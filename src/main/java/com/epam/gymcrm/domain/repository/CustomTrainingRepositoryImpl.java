package com.epam.gymcrm.domain.repository;

import com.epam.gymcrm.application.dto.training.RequestTraineeTrainingsDto;
import com.epam.gymcrm.application.dto.training.RequestTrainerTrainingsDto;
import com.epam.gymcrm.infrastructure.entity.TrainingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomTrainingRepositoryImpl implements CustomTrainingRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TrainingEntity> findTraineeTrainingsByUsernameAndCriteria(RequestTraineeTrainingsDto req){

        List<TrainingEntity> trainingEntities = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TrainingEntity> cq = cb.createQuery(TrainingEntity.class);
        Root<TrainingEntity> trainingEntityRoot = cq.from(TrainingEntity.class);


        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(trainingEntityRoot.get("trainee").get("user").<String> get("username"), req.getUsername()));


        if(req.getPeriodFrom() != null && req.getPeriodTo() != null){
            predicates.add(cb.between(trainingEntityRoot.<Date>get("date"), req.getPeriodFrom(), req.getPeriodTo()));
        } else if(req.getPeriodFrom() != null){
            predicates.add(cb.greaterThanOrEqualTo(trainingEntityRoot.<Date>get("date"), req.getPeriodFrom()));
        }else if(req.getPeriodTo() != null){
            predicates.add(cb.lessThanOrEqualTo(trainingEntityRoot.<Date>get("date"), req.getPeriodTo()));
        }

        if(req.getTrainerName() != null){
            predicates.add(cb.equal(trainingEntityRoot.get("trainer").get("user").<String> get("firstName"), req.getTrainerName()));
        }

        if(req.getTrainingType() != null){
            predicates.add(cb.equal(trainingEntityRoot.get("trainingType").<String>get("name"), req.getTrainingType()));
        }



        cq.select(trainingEntityRoot).where(predicates.toArray(new Predicate[predicates.size()]));

        trainingEntities = em.createQuery(cq).getResultList();


        return trainingEntities;
    }

    @Override
    public List<TrainingEntity> findTrainerTrainingsByUsernameAndCriteria(RequestTrainerTrainingsDto req){

        List<TrainingEntity> trainingEntities = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TrainingEntity> cq = cb.createQuery(TrainingEntity.class);
        Root<TrainingEntity> trainingEntityRoot = cq.from(TrainingEntity.class);


        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(trainingEntityRoot.get("trainee").get("user").<String> get("username"), req.getUsername()));


        if(req.getPeriodFrom() != null && req.getPeriodTo() != null){
            predicates.add(cb.between(trainingEntityRoot.<Date>get("date"), req.getPeriodFrom(), req.getPeriodTo()));
        } else if(req.getPeriodFrom() != null){
            predicates.add(cb.greaterThanOrEqualTo(trainingEntityRoot.<Date>get("date"), req.getPeriodFrom()));
        }else if(req.getPeriodTo() != null){
            predicates.add(cb.lessThanOrEqualTo(trainingEntityRoot.<Date>get("date"), req.getPeriodTo()));
        }

        if(req.getTraineeName() != null){
            predicates.add(cb.equal(trainingEntityRoot.get("trainee").get("user").<String> get("firstName"), req.getTraineeName()));
        }

        if(req.getTrainingType() != null){
            predicates.add(cb.equal(trainingEntityRoot.get("trainingType").<String>get("name"), req.getTrainingType()));
        }



        cq.select(trainingEntityRoot).where(predicates.toArray(new Predicate[predicates.size()]));

        trainingEntities = em.createQuery(cq).getResultList();


        return trainingEntities;
    }
}
