package com.infosys.taskmanager.repository;

import java.util.List;

import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infosys.taskmanager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);
    List<Task> findByStatusAndPriority(Status status, Priority priority);
    
    @Query("SELECT t FROM Task t WHERE " +
            "t.title LIKE CONCAT('%',:query, '%')" +
            "Or t.description LIKE CONCAT('%', :query, '%')")
	List<Task> findByTitleAndDescription(String query);
}

