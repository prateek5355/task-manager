package com.infosys.taskmanager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.taskmanager.entity.Comment;
import com.infosys.taskmanager.entity.Task;

/**
 * 
 */
public interface TaskService {
	
	Task createTask(Task task);

	Page<Task> listTasks(String status, String priority, int page, int size);

	Task getTask(@PathVariable Long id);

	Task updateTask(Long id, Task taskDetails);

	void deleteTask(Long id);

	Task addComment(Long id, Comment comment);
	
	List<Task> searchTasks(String query); 
}
