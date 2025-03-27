package com.approvesystem.controller;

import com.approvesystem.model.Task;
import com.approvesystem.model.User;
import com.approvesystem.repository.UserRepository;
import com.approvesystem.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/tasks")
public class TaskControlle {
    private final TaskService taskService;

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(TaskControlle.class);

    public TaskControlle(TaskService service, TaskService taskService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.taskService = taskService;
    }


    @PostMapping("/create")
    public Task createTask(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Long creatorId,
            @RequestParam("approverIds") List<String> approverIdsStr) {


        List<Long> approverIds = approverIdsStr.stream()
                .map(Long::parseLong)
                .toList();


        return taskService.createTask(title, description, creatorId, approverIds);
    }

    @GetMapping("/users")
    public List<User> getAllUsersExceptCreator(@RequestParam Long creatorId) {
        log.info("Getting all users except creator with ID: {}", creatorId);

        return userRepository.findAll().stream()
                .filter(user -> !user.getId().equals(creatorId))
                .toList();
    }

    @PostMapping("/{taskId}/approve")
    public ResponseEntity<String> approveTask(
            @PathVariable Long taskId,
            @RequestParam Long approverId,
            @RequestParam(required = false) String comment) {

        log.info("Approving task with ID: {} by user: {}, comment: {}",
                taskId, approverId, comment);

        taskService.approveTask(taskId, approverId, comment);
        return ResponseEntity.ok("Task approved.");
    }

    @PostMapping("/{taskId}/comment")
    public ResponseEntity<String> addComment(
            @PathVariable Long taskId,
            @RequestParam Long userId,
            @RequestParam String comment) {

        log.info("Adding comment to task ID: {} by user ID: {}", taskId, userId);

        taskService.addComment(taskId, userId, comment);
        return ResponseEntity.ok("Comment added.");
    }
}
