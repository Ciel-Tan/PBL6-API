package com.app.homeworkoutapplication.module.exerciseattribute.dto;

import com.app.homeworkoutapplication.module.attribute.dto.Attribute;
import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import lombok.Data;

@Data
public class ExerciseAttribute {

    private Long id;

    private Exercise exercise;

    private Attribute attribute;
}
