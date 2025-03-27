package com.approvesystem.controller;

import com.approvesystem.model.Approval;
import com.approvesystem.model.User;
import com.approvesystem.service.ApprovalService;
import com.approvesystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class ApprovalController {

    public AuthController(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    private final UserService userService;

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;



    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");
        String password = request.get("password");

        if (userService.getUserByEmail(email) != null) {
            return ResponseEntity.badRequest().body("User already exists.");
        }

        userService.registerUser(name, email, password);
        return ResponseEntity.ok("User registered successfully!");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");
        String password = request.get("password");

        User user = userService.getUserByEmail(email);

        if (user != null && encoder.matches(password, user.getPassword())) {

            session.setAttribute("USER_ID", user.getId());
            session.setAttribute("EMAIL", user.getEmail());
            return ResponseEntity.ok("Login successful! Session ID: " + session.getId());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials.");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully.");
    }


    @GetMapping("/session")
    public ResponseEntity<String> checkSession(HttpSession session) {
        Long userId = (Long) session.getAttribute("USER_ID");
        if (userId != null) {
            return ResponseEntity.ok("User is logged in with ID: " + userId);
        }
        return ResponseEntity.status(401).body("No active session.");
    }
}
