package com.app.homeworkoutapplication.module.plan.controller;

import com.app.homeworkoutapplication.module.plan.dto.Plan;
import com.app.homeworkoutapplication.module.plan.service.PlanService;
import com.app.homeworkoutapplication.module.plan.service.QueryPlanService;
import com.app.homeworkoutapplication.module.plan.service.criteria.PlanCriteria;
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
public class PlanController {

    private final PlanService planService;

    private final QueryPlanService queryPlanService;

    public PlanController(PlanService planService, QueryPlanService queryPlanService) {
        this.planService = planService;
        this.queryPlanService = queryPlanService;
    }

    @PostMapping("/plans")
    public ResponseEntity<Plan> create(@Valid @RequestBody Plan plan) throws URISyntaxException {
        Plan result = planService.save(plan);
        return ResponseEntity.created(new URI("/api/plans/" + result.getId())).body(result);
    }

    @PutMapping("/plans/{id}")
    public ResponseEntity<Plan> update(@PathVariable Long id, @Valid @RequestBody Plan plan){
        if (plan.getId() == null) plan.setId(id);
        Plan res = planService.save(plan);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/plans")
    public ResponseEntity<List<Plan>> getPages(@ParameterObject PlanCriteria criteria, @ParameterObject Pageable pageable) {
        Page<Plan> page = queryPlanService.findPageByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/plans/all")
    public ResponseEntity<List<Plan>> getAll(@ParameterObject PlanCriteria criteria){
        List<Plan> plans = queryPlanService.findListByCriteria(criteria);
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/plans/{id}")
    public ResponseEntity<Plan> findById(@PathVariable Long id){
        Plan res = queryPlanService.findById(id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        planService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
