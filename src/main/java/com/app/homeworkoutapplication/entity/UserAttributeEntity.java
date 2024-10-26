package com.app.homeworkoutapplication.entity;

import com.app.homeworkoutapplication.entity.enumeration.Unit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "user_attribute")
@ToString
public class UserAttributeEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_focus")
    private Boolean isFocus;

    @Column(name = "measure")
    private Double measure;

    @Column(name = "measure_goal")
    private Double measureGoal;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private Unit unit;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private AttributeEntity attribute;
}
