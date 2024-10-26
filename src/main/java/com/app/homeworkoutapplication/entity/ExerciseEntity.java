package com.app.homeworkoutapplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "exercise")
@ToString
public class ExerciseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "video_path")
    private String videoPath;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseAttributeEntity> exerciseAttributes = new HashSet<>();

    @OneToMany(mappedBy = "exercise")
    private Set<ExercisePlanEntity> exercisePlans = new HashSet<>();

    @OneToMany(mappedBy = "exercise")
    private Set<FavouriteExerciseEntity> favouriteExercises = new HashSet<>();
}
