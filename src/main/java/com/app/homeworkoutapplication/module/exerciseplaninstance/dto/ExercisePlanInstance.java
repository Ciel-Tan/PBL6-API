package com.app.homeworkoutapplication.module.exerciseplaninstance.dto;

import com.app.homeworkoutapplication.entity.ExerciseEntity;
import com.app.homeworkoutapplication.entity.ExercisePlanEntity;
import com.app.homeworkoutapplication.entity.PlanInstanceEntity;
import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import com.app.homeworkoutapplication.module.exerciseplan.dto.ExercisePlan;
import com.app.homeworkoutapplication.module.planinstance.dto.PlanInstance;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.Instant;

@Data
public class ExercisePlanInstance {

    private Long id;

    private Instant dateTime;

    private Long dateOrder;

    private Long setCount;

    private Long repCount;

    private Long exerciseId;

    private Long planInstanceId;

    private Long exercisePlanId;

    private Exercise exercise;

    private PlanInstance planInstance;

    private ExercisePlan exercisePlan;
}
