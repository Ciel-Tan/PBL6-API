package com.app.homeworkoutapplication.module.feedback.service.criteria;

import lombok.Data;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serializable;

@Data
public class FeedbackCriteria implements Serializable {

    private LongFilter id;

    private StringFilter comment;

    private LongFilter userId;

    private LongFilter planId;
}
