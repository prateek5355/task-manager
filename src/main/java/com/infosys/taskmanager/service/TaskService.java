package com.infosys.taskmanager.service;

import java.util.List;

import com.infosys.taskmanager.dto.CommentDto;
import com.infosys.taskmanager.dto.TaskDeleteResponse;
import com.infosys.taskmanager.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.taskmanager.entity.Comment;
import com.infosys.taskmanager.entity.Task;

/**
 * 
 */
public interface TaskService {
	
	Task createTask(TaskDto task);

	Page<Task> listTasks(String status, String priority, int page, int size);

	Task getTask(@PathVariable Long id);

	Task updateTask(Long id, Task taskDetails);
	TaskDeleteResponse deleteTask(Long id);

	Task addComment(Long id, CommentDto comment);
	
	List<Task> searchTasks(String query);
}
