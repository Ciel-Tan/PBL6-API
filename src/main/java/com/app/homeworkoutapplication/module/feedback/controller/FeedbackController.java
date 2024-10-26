package com.app.homeworkoutapplication.module.feedback.controller;

import com.app.homeworkoutapplication.module.feedback.dto.Feedback;
import com.app.homeworkoutapplication.module.feedback.service.FeedbackService;
import com.app.homeworkoutapplication.module.feedback.service.QueryFeedbackService;
import com.app.homeworkoutapplication.module.feedback.service.criteria.FeedbackCriteria;
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
public class FeedbackController {

    private final FeedbackService feedbackService;

    private final QueryFeedbackService queryFeedbackService;

    public FeedbackController(FeedbackService feedbackService, QueryFeedbackService queryFeedbackService) {
        this.feedbackService = feedbackService;
        this.queryFeedbackService = queryFeedbackService;
    }

    @PostMapping("/feedbacks")
    public ResponseEntity<Feedback> create(@Valid @RequestBody Feedback feedback) throws URISyntaxException {
        Feedback result = feedbackService.save(feedback);
        return ResponseEntity.created(new URI("/api/feedbacks/" + result.getId())).body(result);
    }

    @PutMapping("/feedbacks/{id}")
    public ResponseEntity<Feedback> update(@PathVariable Long id, @Valid @RequestBody Feedback feedback){
        if (feedback.getId() == null) feedback.setId(id);
        Feedback res = feedbackService.save(feedback);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<List<Feedback>> getPages(@ParameterObject FeedbackCriteria criteria, @ParameterObject Pageable pageable) {
        Page<Feedback> page = queryFeedbackService.findPageByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/feedbacks/all")
    public ResponseEntity<List<Feedback>> getAll(@ParameterObject FeedbackCriteria criteria){
        List<Feedback> feedbacks = queryFeedbackService.findListByCriteria(criteria);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<Feedback> findById(@PathVariable Long id){
        Feedback res = queryFeedbackService.findById(id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
