package com.approvesystem.controller;

import com.approvesystem.model.User;
import com.approvesystem.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ApprovalController {
    private final UserService service;


    public ApprovalController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public User signup(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        return service.createUser(name, email, password);
    }
}
