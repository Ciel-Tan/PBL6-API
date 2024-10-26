package com.app.homeworkoutapplication.repository;

import com.app.homeworkoutapplication.entity.ExerciseAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseAttributeRepository extends JpaRepository<ExerciseAttributeEntity, Long>, JpaSpecificationExecutor<ExerciseAttributeEntity> {
}
