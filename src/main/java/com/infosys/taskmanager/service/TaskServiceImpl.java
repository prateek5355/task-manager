package com.infosys.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	public Task createTask(Task task) {
		task.setCreator("current-user"); // For simplicity, hardcoding the creator
		return taskRepository.save(task);
	}

	public Page<Task> listTasks(String status, String priority, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		if (status != null && priority != null) {
			return taskRepository.findByStatusAndPriority(status, priority, pageable);
		} else {
			return taskRepository.findAll(pageable);
		}
	}

	public Task getTask(@PathVariable Long id) {
		 Optional<Task> task = taskRepository.findById(id);
	        return task.orElse(null);
	}
	
	 public Task updateTask(Long id,Task taskDetails) {
	        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
	        task.setTitle(taskDetails.getTitle());
	        task.setDescription(taskDetails.getDescription());
	        task.setStatus(taskDetails.getStatus());
	        task.setPriority(taskDetails.getPriority());
	        task.setAssignee(taskDetails.getAssignee());
	        task.setDueDate(taskDetails.getDueDate());
	        return taskRepository.save(task);
	    }

	    public void deleteTask(Long id) {
	        taskRepository.deleteById(id);
	    }
	    
	    public Task addComment(Long id, Comment comment) {
	        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
	        task.getComments().add(comment);
	        return taskRepository.save(task);
	    }
	    
	    // Search tasks (Optional)
	    public List<Task> searchTasks(String query) {
	        return taskRepository.findByDescriptionContaining(query); // For example, searching by description
	    }

}
