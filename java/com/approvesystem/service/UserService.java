package com.approvesystem.service;

import com.approvesystem.model.User;
import com.approvesystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, UserRepository userRepository) {
        this.userRepository = repository;
    }

    public User createUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        return userRepository.save(user);
    }



    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User registerUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        return user;
    }
}


