package com.infosys.taskmanager.service;

import java.util.List;

import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.taskmanager.dto.CommentDto;
import com.infosys.taskmanager.dto.TaskDeleteResponse;
import com.infosys.taskmanager.dto.TaskDto;
import com.infosys.taskmanager.entity.Task;

/**
 * 
 */
public interface TaskService {
	
	Task createTask(TaskDto task);

	List<Task> listTasks(Status status, Priority priority);

	Task getTask(@PathVariable Long id);

	Task updateTask(Long id, TaskDto taskDetails);
	TaskDeleteResponse deleteTask(Long id);

	Task addComment(Long id, CommentDto comment);
	
	List<Task> searchTasks(String keyword);
}
