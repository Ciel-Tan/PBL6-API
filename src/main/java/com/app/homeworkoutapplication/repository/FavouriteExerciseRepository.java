package com.app.homeworkoutapplication.repository;

import com.app.homeworkoutapplication.entity.FavouriteExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteExerciseRepository extends JpaRepository<FavouriteExerciseEntity, Long>, JpaSpecificationExecutor<FavouriteExerciseEntity> {
}
