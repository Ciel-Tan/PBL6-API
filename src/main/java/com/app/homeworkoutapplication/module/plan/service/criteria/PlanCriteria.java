package com.app.homeworkoutapplication.module.plan.service.criteria;

import com.app.homeworkoutapplication.entity.filter.PlanStatusFilter;
import lombok.Data;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serializable;

@Data
public class PlanCriteria implements Serializable {

    private LongFilter id;

    private StringFilter name;

    private StringFilter description;

    private PlanStatusFilter status;

    private IntegerFilter totalDays;

    private FloatFilter rating;

    private LongFilter userId;
}
