package com.app.homeworkoutapplication.entity;

import com.app.homeworkoutapplication.entity.enumeration.PlanStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "plan")
@ToString
public class PlanEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PlanStatus status;

    @Column(name = "total_days")
    private Integer totalDays;

    @Column(name = "rating")
    private Float rating;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "plan")
    private Set<ExercisePlanEntity> exercisePlans = new HashSet<>();

    @OneToMany(mappedBy = "plan")
    private Set<FeedbackEntity> feedbacks = new HashSet<>();
}
