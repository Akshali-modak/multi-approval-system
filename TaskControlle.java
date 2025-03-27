package com.approvesystem.controller;

import com.approvesystem.model.Task;
import com.approvesystem.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskControlle {
    private final TaskService service;

    public TaskControlle(TaskService service) {
        this.service = service;
    }


    @PostMapping("/create")
    public Task createTask(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Long creatorId,
            @RequestParam List<Long> approverIds) {

        return service.createTask(title, description, creatorId, approverIds);
    }
}
