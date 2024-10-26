package com.app.homeworkoutapplication.module.exerciseplan.controller;

import com.app.homeworkoutapplication.module.exerciseplan.dto.ExercisePlan;
import com.app.homeworkoutapplication.module.exerciseplan.service.ExercisePlanService;
import com.app.homeworkoutapplication.module.exerciseplan.service.QueryExercisePlanService;
import com.app.homeworkoutapplication.module.exerciseplan.service.criteria.ExercisePlanCriteria;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Authentication")
public class ExercisePlanController {

    private final ExercisePlanService exercisePlanService;

    private final QueryExercisePlanService queryExercisePlanService;

    public ExercisePlanController(ExercisePlanService exercisePlanService, QueryExercisePlanService queryExercisePlanService) {
        this.exercisePlanService = exercisePlanService;
        this.queryExercisePlanService = queryExercisePlanService;
    }

    @PostMapping("/exercise-plans")
    public ResponseEntity<ExercisePlan> create(@Valid @RequestBody ExercisePlan exercisePlan) throws URISyntaxException {
        ExercisePlan result = exercisePlanService.save(exercisePlan);
        return ResponseEntity.created(new URI("/api/exercise-plans/" + result.getId())).body(result);
    }

    @PutMapping("/exercise-plans/{id}")
    public ResponseEntity<ExercisePlan> update(@PathVariable Long id, @Valid @RequestBody ExercisePlan exercisePlan){
        if (exercisePlan.getId() == null) exercisePlan.setId(id);
        ExercisePlan res = exercisePlanService.save(exercisePlan);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/exercise-plans")
    public ResponseEntity<List<ExercisePlan>> getPages(@ParameterObject ExercisePlanCriteria criteria,@ParameterObject  Pageable pageable) {
        Page<ExercisePlan> page = queryExercisePlanService.findPageByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/exercise-plans/all")
    public ResponseEntity<List<ExercisePlan>> getAll(@ParameterObject ExercisePlanCriteria criteria){
        List<ExercisePlan> feedbacks = queryExercisePlanService.findListByCriteria(criteria);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/exercise-plans/{id}")
    public ResponseEntity<ExercisePlan> findById(@PathVariable Long id){
        ExercisePlan res = queryExercisePlanService.findById(id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/exercise-plans/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        exercisePlanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
