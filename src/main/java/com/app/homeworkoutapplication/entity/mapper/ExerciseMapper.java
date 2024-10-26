package com.app.homeworkoutapplication.entity.mapper;

import com.app.homeworkoutapplication.entity.ExerciseEntity;
import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { })
public interface ExerciseMapper extends EntityMapper<Exercise, ExerciseEntity> {
}
