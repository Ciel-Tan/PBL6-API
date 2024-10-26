package com.app.homeworkoutapplication.entity.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ExerciseMapper.class, PlanInstanceMapper.class, ExercisePlanMapper.class })
public interface ExercisePlanInstanceMapper {
}
