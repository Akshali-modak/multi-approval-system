package com.approvesystem.service;

import com.approvesystem.model.Approval;
import com.approvesystem.model.Task;
import com.approvesystem.model.User;
import com.approvesystem.repository.ApprovalRepository;
import com.approvesystem.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalService {
    @Autowired
    private ApprovalRepository approvalRepository;
    @Autowired
    private TaskRepository taskRepository;

    public Approval approveTask(Long taskId, Long userId, String comment) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        Approval approval = new Approval();
        approval.setTask(task);
        approval.setApprover(new User(userId));
        approval.setComment(comment);
        approvalRepository.save(approval);

        List<Approval> approvals = approvalRepository.findByTaskId(taskId);
        if (approvals.size() >= 3) {
            task.setStatus("APPROVED");
            taskRepository.save(task);
        }
        return approval;
    }
}
