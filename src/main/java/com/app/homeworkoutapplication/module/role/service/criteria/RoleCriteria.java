package com.app.homeworkoutapplication.module.role.service.criteria;


import lombok.Data;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

@Data
public class RoleCriteria {

    private LongFilter id;

    private StringFilter name;
}
