package com.app.homeworkoutapplication.module.exerciseattribute.service.criteria;

import lombok.Data;
import tech.jhipster.service.filter.LongFilter;

import java.io.Serializable;

@Data
public class ExerciseAttributeCriteria implements Serializable {

    private LongFilter id;

    private LongFilter exerciseId;

    private LongFilter attributeId;
}
