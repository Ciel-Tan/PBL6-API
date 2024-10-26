package com.app.homeworkoutapplication.module.attribute.service.criteria;

import lombok.Data;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serializable;

@Data
public class AttributeCriteria implements Serializable {

    private LongFilter id;

    private StringFilter name;
}
