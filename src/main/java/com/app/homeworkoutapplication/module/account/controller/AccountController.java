package com.app.homeworkoutapplication.module.account.controller;

import com.app.homeworkoutapplication.module.account.dto.RegisterRequest;
import com.app.homeworkoutapplication.module.account.service.AccountService;
import com.app.homeworkoutapplication.module.user.dto.User;
import com.app.homeworkoutapplication.module.user.service.QueryUserService;
import com.app.homeworkoutapplication.security.AuthorityConstant;
import com.app.homeworkoutapplication.util.CurrentUserUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Authentication")
public class AccountController {

    private final AccountService accountService;

    private final CurrentUserUtil currentUserUtil;

    public AccountController(AccountService accountService, CurrentUserUtil currentUserUtil) {
        this.accountService = accountService;
        this.currentUserUtil = currentUserUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = accountService.register(registerRequest, "USER");
        return ResponseEntity.ok(user);
    }

    @GetMapping("/activate")
    public ResponseEntity<Void> activateUser(@RequestParam("email") String email) {
        accountService.activateUser(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/account")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(currentUserUtil.getCurrentUser());
    }
}
