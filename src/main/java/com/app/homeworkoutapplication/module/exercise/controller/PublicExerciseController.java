package com.app.homeworkoutapplication.module.exercise.controller;

import com.app.homeworkoutapplication.module.exercise.dto.Exercise;
import com.app.homeworkoutapplication.module.exercise.service.QueryExerciseService;
import com.app.homeworkoutapplication.module.exercise.service.criteria.ExerciseCriteria;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.List;

@RestController
@RequestMapping("/public/api")
public class PublicExerciseController {

    private final QueryExerciseService queryExerciseService;

    public PublicExerciseController(QueryExerciseService queryExerciseService) {
        this.queryExerciseService = queryExerciseService;
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getPages(@ParameterObject ExerciseCriteria criteria, @ParameterObject Pageable pageable) {
        Page<Exercise> page = queryExerciseService.findPageByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/exercises/all")
    public ResponseEntity<List<Exercise>> getAll(@ParameterObject ExerciseCriteria criteria){
        List<Exercise> attributes = queryExerciseService.findListByCriteria(criteria);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<Exercise> findById(@PathVariable Long id){
        Exercise res = queryExerciseService.findById(id);
        return ResponseEntity.ok(res);
    }

}

