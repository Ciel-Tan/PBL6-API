package com.app.homeworkoutapplication.module.user.controller;

import com.app.homeworkoutapplication.module.user.dto.User;
import com.app.homeworkoutapplication.module.user.service.QueryUserService;
import com.app.homeworkoutapplication.module.user.service.UserService;
import com.app.homeworkoutapplication.module.user.service.criteria.UserCriteria;
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
public class UserController {
    
    private final UserService userService;
    
    private final QueryUserService queryUserService;

    public UserController(UserService userService, QueryUserService queryUserService) {
        this.userService = userService;
        this.queryUserService = queryUserService;
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<User> create(@Valid @RequestBody User user) throws URISyntaxException {
        User result = userService.save(user);
        return ResponseEntity.created(new URI("/api/users/" + result.getId())).body(result);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user){
        if (user.getId() == null) user.setId(id);
        User res = userService.save(user);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<List<User>> getPages(@ParameterObject UserCriteria criteria, @ParameterObject Pageable pageable) {
        Page<User> page = queryUserService.findPageByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<List<User>> getAll(@ParameterObject UserCriteria criteria){
        List<User> Users = queryUserService.findListByCriteria(criteria);
        return ResponseEntity.ok(Users);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User res = queryUserService.findById(id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthorityConstant.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
