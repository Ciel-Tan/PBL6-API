package com.app.homeworkoutapplication.repository;

import com.app.homeworkoutapplication.entity.ExercisePlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercisePlanRepository extends JpaRepository<ExercisePlanEntity, Long>, JpaSpecificationExecutor<ExercisePlanEntity> {
}
