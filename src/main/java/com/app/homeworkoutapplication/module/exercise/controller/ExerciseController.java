package com.app.homeworkoutapplication.module.exercise.controller;

import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import com.app.homeworkoutapplication.module.exercise.service.ExerciseService;
import com.app.homeworkoutapplication.module.exercise.service.QueryExerciseService;
import com.app.homeworkoutapplication.module.exercise.service.criteria.ExerciseCriteria;
import com.app.homeworkoutapplication.security.AuthorityConstant;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/api")
public class ExerciseController {

    private final ExerciseService exerciseService;

    private final QueryExerciseService queryExerciseService;

    public ExerciseController(ExerciseService exerciseService, QueryExerciseService queryExerciseService) {
        this.exerciseService = exerciseService;
        this.queryExerciseService = queryExerciseService;
    }

    @PostMapping("/exercises")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<Exercise> create(@Valid @RequestBody Exercise exercise) throws URISyntaxException {
        Exercise result = exerciseService.save(exercise);
        return ResponseEntity.created(new URI("/api/exercises/" + result.getId())).body(result);
    }

    @PutMapping("/exercises/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<Exercise> update(@PathVariable Long id, @Valid @RequestBody Exercise exercise){
        if (exercise.getId() == null) exercise.setId(id);
        Exercise res = exerciseService.save(exercise);
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/exercises/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        exerciseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
