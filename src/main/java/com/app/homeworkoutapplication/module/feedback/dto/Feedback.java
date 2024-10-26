package com.app.homeworkoutapplication.module.feedback.dto;

import com.app.homeworkoutapplication.module.plan.dto.Plan;
import com.app.homeworkoutapplication.module.user.dto.User;
import lombok.Data;

@Data
public class Feedback {
    private Long id;

    private String comment;

    private User user;

    private Plan plan;
}
