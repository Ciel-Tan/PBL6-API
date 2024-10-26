package com.app.homeworkoutapplication.module.userattribute.controller;

import com.app.homeworkoutapplication.module.userattribute.dto.UserAttribute;
import com.app.homeworkoutapplication.module.userattribute.service.QueryUserAttributeService;
import com.app.homeworkoutapplication.module.userattribute.service.UserAttributeService;
import com.app.homeworkoutapplication.module.userattribute.service.criteria.UserAttributeCriteria;
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
public class UserAttributeController {

    private final UserAttributeService userAttributeService;

    private final QueryUserAttributeService queryUserAttributeService;


    public UserAttributeController(UserAttributeService userAttributeService, QueryUserAttributeService queryUserAttributeService) {
        this.userAttributeService = userAttributeService;
        this.queryUserAttributeService = queryUserAttributeService;
    }

    @PostMapping("/user-attributes")
    public ResponseEntity<UserAttribute> create(@Valid @RequestBody UserAttribute userAttribute) throws URISyntaxException {
        UserAttribute result = userAttributeService.save(userAttribute);
        return ResponseEntity.created(new URI("/api/user-attributes/" + result.getId())).body(result);
    }

    @PutMapping("/user-attributes/{id}")
    public ResponseEntity<UserAttribute> update(@PathVariable Long id, @Valid @RequestBody UserAttribute userAttribute){
        if (userAttribute.getId() == null) userAttribute.setId(id);
        UserAttribute res = userAttributeService.save(userAttribute);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user-attributes")
    public ResponseEntity<List<UserAttribute>> getPages(@ParameterObject UserAttributeCriteria criteria, @ParameterObject Pageable pageable) {
        Page<UserAttribute> page = queryUserAttributeService.findPageByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/user-attributes/all")
    public ResponseEntity<List<UserAttribute>> getAll(@ParameterObject UserAttributeCriteria criteria){
        List<UserAttribute> attributes = queryUserAttributeService.findListByCriteria(criteria);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/user-attributes/{id}")
    public ResponseEntity<UserAttribute> findById(@PathVariable Long id){
        UserAttribute res = queryUserAttributeService.findById(id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/user-attributes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userAttributeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
