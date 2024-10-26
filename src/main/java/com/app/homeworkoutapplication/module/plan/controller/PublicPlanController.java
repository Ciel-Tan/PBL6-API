package com.app.homeworkoutapplication.module.plan.controller;


import com.app.homeworkoutapplication.module.plan.dto.Plan;
import com.app.homeworkoutapplication.module.plan.service.PlanService;
import com.app.homeworkoutapplication.module.plan.service.QueryPlanService;
import com.app.homeworkoutapplication.module.plan.service.criteria.PlanCriteria;
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
public class PublicPlanController {

    private final QueryPlanService queryPlanService;

    public PublicPlanController(QueryPlanService queryPlanService) {
        this.queryPlanService = queryPlanService;
    }

    @GetMapping("/plans")
    public ResponseEntity<List<Plan>> getPages(@ParameterObject PlanCriteria criteria, @ParameterObject Pageable pageable) {
        Page<Plan> page = queryPlanService.findPublicPageByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/plans/all")
    public ResponseEntity<List<Plan>> getAll(@ParameterObject PlanCriteria criteria){
        List<Plan> plans = queryPlanService.findPublicListByCriteria(criteria);
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/plans/{id}")
        public ResponseEntity<Plan> findById(@PathVariable Long id){
        Plan res = queryPlanService.findById(id);
        return ResponseEntity.ok(res);
    }
}

