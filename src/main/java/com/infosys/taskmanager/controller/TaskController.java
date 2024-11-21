package com.infosys.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.taskmanager.dto.CommentDto;
import com.infosys.taskmanager.dto.TaskDeleteResponse;
import com.infosys.taskmanager.dto.TaskDto;
import com.infosys.taskmanager.entity.Task;
import com.infosys.taskmanager.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * @param task
     * @return
     */
    @PostMapping
    public Task createTask(@Valid @RequestBody TaskDto task) {
        return taskService.createTask(task);
    }

    /**
     * @param status
     * @param priority
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public Page<Task> listTasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

    	return taskService.listTasks(status,priority,page,size);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
		return taskService.getTask(id); 
    }

    /**
     * @param id
     * @param taskDetails
     * @return
     */
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskDto taskDetails) {
        return taskService.updateTask(id, taskDetails);
    }

    /**
     * @param id
     */
    @DeleteMapping("/{id}")
    public TaskDeleteResponse deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
        }

    /**
     * @param id
     * @param comment
     * @return
     */
    @PostMapping("/{id}/comments")
    public Task addComment(@PathVariable Long id,  @Valid  @RequestBody CommentDto comment) {
        return taskService.addComment(id, comment);
    }

    // Search tasks (Optional)
    /**
     * @param query
     * @return
     */
    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String keyword) {
        return taskService.searchTasks(keyword); // For example, searching by description
    }
}
