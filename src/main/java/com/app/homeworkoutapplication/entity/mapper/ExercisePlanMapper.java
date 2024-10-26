package com.app.homeworkoutapplication.entity.mapper;

import com.app.homeworkoutapplication.entity.ExercisePlanEntity;
import com.app.homeworkoutapplication.module.exerciseplan.dto.ExercisePlan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ExerciseMapper.class, PlanMapper.class })
public interface ExercisePlanMapper extends EntityMapper<ExercisePlan, ExercisePlanEntity> {
}
