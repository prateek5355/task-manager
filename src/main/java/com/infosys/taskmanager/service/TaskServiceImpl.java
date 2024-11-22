package com.infosys.taskmanager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.infosys.taskmanager.dto.CommentDto;
import com.infosys.taskmanager.dto.TaskDeleteResponse;
import com.infosys.taskmanager.dto.TaskDto;
import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.taskmanager.entity.Comment;
import com.infosys.taskmanager.entity.Task;
import com.infosys.taskmanager.repository.TaskRepository;

/**
 * 
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task createTask(TaskDto taskDto) {
		Task task = new Task();
		task.setTitle(taskDto.getTitle());
		task.setDescription(taskDto.getDescription());
		task.setPriority(taskDto.getPriority());
		task.setAssignee(taskDto.getAssignee());
		task.setDueDate(taskDto.getDueDate());
		task.setStatus(Status.TODO);
		task.setCreator("current-user");
		return taskRepository.save(task);
	}

	public List<Task> listTasks(Status status, Priority priority) {
		if (status != null && priority != null) {
			return taskRepository.findByStatusAndPriority(status, priority);
		} else if (status != null) {
			return taskRepository.findByStatus(status);
		} else if (priority != null) {
			return taskRepository.findByPriority(priority);
		} else {
			return taskRepository.findAll();
		}
	}

	public Task getTask(@PathVariable Long id) {
		 Optional<Task> task = taskRepository.findById(id);
	        return task.orElse(null);
	}
	
	 public Task updateTask(Long id,TaskDto taskDetails) {
	        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
	        task.setTitle(taskDetails.getTitle());
	        task.setDescription(taskDetails.getDescription());
	        task.setPriority(taskDetails.getPriority());
	        task.setAssignee(taskDetails.getAssignee());
	        task.setDueDate(taskDetails.getDueDate());
	        return taskRepository.save(task);
	    }

	    public TaskDeleteResponse deleteTask(Long id) {
	        taskRepository.deleteById(id);
			TaskDeleteResponse taskDeleteResponse = new TaskDeleteResponse();
			taskDeleteResponse.setMessage("Task with id:" +id+ " is deleted successfully");
			return taskDeleteResponse;
	    }
	    
	    public Task addComment(Long id, CommentDto commentDto) {
	        Task task = taskRepository.findById(id).orElseThrow(() -> new NullPointerException("Task not found"));
			if (task.getComments() == null) {
				task.setComments(new ArrayList<>());
			}
			Comment comment = new Comment();
			comment.setText(commentDto.getText());
			comment.setAuthor(commentDto.getAuthor());
			comment.setCreatedAt(new Date());
			task.getComments().add(comment);
	        return taskRepository.save(task);
	    }

	    public List<Task> searchTasks(String keyword) {
	        return taskRepository.findByTitleAndDescription(keyword);
	    }

}
