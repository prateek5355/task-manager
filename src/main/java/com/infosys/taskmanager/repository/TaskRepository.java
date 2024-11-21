package com.infosys.taskmanager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infosys.taskmanager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatusAndPriority(String status, String priority, Pageable pageable);
    Page<Task> findByCreator(String creator, Pageable pageable);
    Page<Task> findByAssignee(String assignee, Pageable pageable);
    
    @Query("SELECT t FROM Task t WHERE " +
            "t.title LIKE CONCAT('%',:query, '%')" +
            "Or t.description LIKE CONCAT('%', :query, '%')")
	List<Task> findByTitleAndDescription(String query);
}

