package com.app.homeworkoutapplication.module.planinstance.dto;

import com.app.homeworkoutapplication.module.exerciseplaninstance.dto.ExercisePlanInstance;
import com.app.homeworkoutapplication.module.plan.dto.Plan;
import lombok.Data;

import java.util.List;

@Data
public class PlanInstance {

    private Long id;

    private String name;

    private String description;

    private Integer totalDays;

    private Long planId;

    private Plan plan;

    private List<ExercisePlanInstance> exercisePlanInstances;
}
