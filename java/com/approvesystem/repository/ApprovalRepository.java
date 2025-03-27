package com.approvesystem.repository;

import com.approvesystem.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
    List<Approval> findByTaskId(Long taskId);
}
