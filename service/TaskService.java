package com.approvesystem.service;

import com.approvesystem.model.Task;
import com.approvesystem.model.User;
import com.approvesystem.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public Task createTask(String title, String description, Long createdById, List<Long> approverIds) {
        User creator = userRepository.findById(createdById)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus("PENDING");
        task.setCreatedBy(creator);
        task.setApproverIds(approverIds);

        Task savedTask = taskRepository.save(task);

        for (Long approverId : approverIds) {
            User approver = userRepository.findById(approverId)
                    .orElseThrow(() -> new RuntimeException("Approver not found"));

            emailService.sendEmail(approver.getEmail(), "Task Approval", "You have a new task to approve.");
        }

        return savedTask;
    }
}
