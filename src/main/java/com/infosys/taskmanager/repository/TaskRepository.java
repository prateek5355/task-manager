package com.infosys.taskmanager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.taskmanager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatusAndPriority(String status, String priority, Pageable pageable);
    Page<Task> findByCreator(String creator, Pageable pageable);
    Page<Task> findByAssignee(String assignee, Pageable pageable);
	List<Task> findByDescriptionContaining(String query);
}

