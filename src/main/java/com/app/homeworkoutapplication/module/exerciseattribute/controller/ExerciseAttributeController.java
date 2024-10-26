package com.app.homeworkoutapplication.module.exerciseattribute.controller;

import com.app.homeworkoutapplication.module.exerciseattribute.dto.ExerciseAttribute;
import com.app.homeworkoutapplication.module.exerciseattribute.service.ExerciseAttributeService;
import com.app.homeworkoutapplication.module.exerciseattribute.service.QueryExerciseAttributeService;
import com.app.homeworkoutapplication.module.exerciseattribute.service.criteria.ExerciseAttributeCriteria;
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
@RequestMapping("/api")
@SecurityRequirement(name = "Authentication")
public class ExerciseAttributeController {

    private final ExerciseAttributeService exerciseAttributeService;

    private final QueryExerciseAttributeService queryExerciseAttributeService;

    public ExerciseAttributeController(ExerciseAttributeService exerciseAttributeService, QueryExerciseAttributeService queryExerciseAttributeService) {
        this.exerciseAttributeService = exerciseAttributeService;
        this.queryExerciseAttributeService = queryExerciseAttributeService;
    }

    @PostMapping("/exercise-attributes")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<ExerciseAttribute> create(@Valid @RequestBody ExerciseAttribute exerciseAttribute) throws URISyntaxException {
        ExerciseAttribute result = exerciseAttributeService.save(exerciseAttribute);
        return ResponseEntity.created(new URI("/api/exercise-attributes/" + result.getId())).body(result);
    }

    @PutMapping("/exercise-attributes/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<ExerciseAttribute> update(@PathVariable Long id, @Valid @RequestBody ExerciseAttribute exerciseAttribute){
        if (exerciseAttribute.getId() == null) exerciseAttribute.setId(id);
        ExerciseAttribute res = exerciseAttributeService.save(exerciseAttribute);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/exercise-attributes")
    public ResponseEntity<List<ExerciseAttribute>> getPages(@ParameterObject ExerciseAttributeCriteria criteria, @ParameterObject Pageable pageable) {
        Page<ExerciseAttribute> page = queryExerciseAttributeService.findPageByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/exercise-attributes/all")
    public ResponseEntity<List<ExerciseAttribute>> getAll(@ParameterObject ExerciseAttributeCriteria criteria){
        List<ExerciseAttribute> exerciseAttributes = queryExerciseAttributeService.findListByCriteria(criteria);
        return ResponseEntity.ok(exerciseAttributes);
    }

    @GetMapping("/exercise-attributes/{id}")
    public ResponseEntity<ExerciseAttribute> findById(@PathVariable Long id){
        ExerciseAttribute res = queryExerciseAttributeService.findById(id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/exercise-attributes/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        exerciseAttributeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
