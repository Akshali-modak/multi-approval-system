package com.approvesystem.repository;

import com.approvesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositor  extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
