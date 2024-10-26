package com.app.homeworkoutapplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "plan_instance")
@ToString
public class PlanInstanceEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "total_days")
    private Integer totalDays;

    @ManyToOne
    private PlanEntity plan;

    @OneToMany(mappedBy = "planInstance")
    private Set<ExercisePlanInstanceEntity> exercisePlanInstance = new HashSet<>();
}