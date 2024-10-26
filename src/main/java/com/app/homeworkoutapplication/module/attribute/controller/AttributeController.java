package com.app.homeworkoutapplication.module.attribute.controller;

import com.app.homeworkoutapplication.module.attribute.dto.Attribute;
import com.app.homeworkoutapplication.module.attribute.service.AttributeService;
import com.app.homeworkoutapplication.module.attribute.service.QueryAttributeService;
import com.app.homeworkoutapplication.module.attribute.service.criteria.AttributeCriteria;
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
public class AttributeController {

    private final AttributeService attributeService;

    private final QueryAttributeService queryAttributeService;

    public AttributeController(AttributeService attributeService, QueryAttributeService queryAttributeService) {
        this.attributeService = attributeService;
        this.queryAttributeService = queryAttributeService;
    }

    @PostMapping("/attributes")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<Attribute> create(@Valid @RequestBody Attribute attribute) throws URISyntaxException {
        Attribute result = attributeService.save(attribute);
        return ResponseEntity.created(new URI("/api/attributes/" + result.getId())).body(result);
    }

    @PutMapping("/attributes/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<Attribute> update(@PathVariable Long id, @Valid @RequestBody Attribute attribute){
        if (attribute.getId() == null) attribute.setId(id);
        Attribute res = attributeService.save(attribute);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/attributes")
    public ResponseEntity<List<Attribute>> getPages(@ParameterObject AttributeCriteria criteria, @ParameterObject Pageable pageable) {
        Page<Attribute> page = queryAttributeService.findPageByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/attributes/all")
    public ResponseEntity<List<Attribute>> getAll(@ParameterObject AttributeCriteria criteria){
        List<Attribute> attributes = queryAttributeService.findListByCriteria(criteria);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/attributes/{id}")
    public ResponseEntity<Attribute> findById(@PathVariable Long id){
        Attribute res = queryAttributeService.findById(id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/attributes/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        attributeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
