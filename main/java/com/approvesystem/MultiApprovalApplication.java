package com.approvesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class MultiApprovalApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiApprovalApplication.class, args);
    }
}
