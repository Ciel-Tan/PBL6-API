package com.app.homeworkoutapplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "attribute")
@ToString
public class AttributeEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @OneToMany(mappedBy = "attribute")
    private Set<ExerciseAttributeEntity> exerciseAttributes = new HashSet<>();

    @OneToMany(mappedBy = "attribute")
    private Set<UserAttributeEntity> userAttributes = new HashSet<>();
}
