package com.infosys.taskmanager.repository;

import com.infosys.taskmanager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatusAndPriority(String status, String priority, Pageable pageable);
    Page<Task> findByCreator(String creator, Pageable pageable);
    Page<Task> findByAssignee(String assignee, Pageable pageable);
}

