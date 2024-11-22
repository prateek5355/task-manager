package com.infosys.taskmanager.controller;

import java.util.List;

import com.infosys.taskmanager.dto.TaskResponse;
import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Get all tasks and the total count.
     *
     * @return TaskResponse containing the total count and the list of tasks.
     */

    @GetMapping
    public TaskResponse listTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority) {
        List<Task> tasks = taskService.listTasks(status, priority);
        TaskResponse response = new TaskResponse();
        response.setTotal(tasks.size());
        response.setTasks(tasks);
        return response;
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
