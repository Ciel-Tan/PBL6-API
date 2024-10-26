package com.app.homeworkoutapplication.module.exerciseplan.service.criteria;

import lombok.Data;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serializable;

@Data
public class ExercisePlanCriteria implements Serializable {

    private LongFilter id;

    private StringFilter time;

    private LongFilter dateOrder;

    private LongFilter setCount;

    private LongFilter repCount;

    private LongFilter exerciseId;

    private LongFilter planId;
}
