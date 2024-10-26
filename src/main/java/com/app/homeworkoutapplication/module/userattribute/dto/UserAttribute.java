package com.app.homeworkoutapplication.module.userattribute.dto;

import com.app.homeworkoutapplication.module.attribute.dto.Attribute;
import com.app.homeworkoutapplication.module.user.dto.User;
import lombok.Data;

@Data
public class UserAttribute {
    private Long id;

    private String name;

    private Boolean isFocus;

    private Double measure;

    private Double measureGoal;

    private String unit;

    private User user;

    private Attribute attribute;
}
