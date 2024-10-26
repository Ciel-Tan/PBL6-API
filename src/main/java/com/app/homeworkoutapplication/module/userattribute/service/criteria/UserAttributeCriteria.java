package com.app.homeworkoutapplication.module.userattribute.service.criteria;

import com.app.homeworkoutapplication.entity.filter.UnitFilter;
import lombok.Data;
import tech.jhipster.service.filter.*;

import java.io.Serializable;

@Data
public class UserAttributeCriteria implements Serializable {

    private LongFilter id;

    private StringFilter name;

    private BooleanFilter isFocus;

    private DoubleFilter measure;

    private DoubleFilter measureGoal;
    
    private UnitFilter unit;
    
    private LongFilter userId;
    
    private LongFilter attributeId;
}
