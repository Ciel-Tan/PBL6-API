package com.app.homeworkoutapplication.repository;

import com.app.homeworkoutapplication.entity.ExerciseEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Registered
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long>, JpaSpecificationExecutor<ExerciseEntity> {
}
