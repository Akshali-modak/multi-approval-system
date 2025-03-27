package com.approvesystem.service;

import com.approvesystem.model.User;
import com.approvesystem.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        User user = new User(null, user.getEmail(), user.getName(), hashedPassword);
        userRepository.save(user);
        return "User registered successfully!";
    }
}
