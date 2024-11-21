package com.infosys.taskmanager.controller;

import com.infosys.taskmanager.entity.Comment;
import com.infosys.taskmanager.entity.Task;
import com.infosys.taskmanager.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        task.setCreator("current-user"); // For simplicity, hardcoding the creator
        return taskRepository.save(task);
    }

    @GetMapping
    public Page<Task> listTasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        if (status != null && priority != null) {
            return taskRepository.findByStatusAndPriority(status, priority, pageable);
        } else {
            return taskRepository.findAll(pageable);
        }
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setAssignee(taskDetails.getAssignee());
        task.setDueDate(taskDetails.getDueDate());
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PostMapping("/{id}/comments")
    public Task addComment(@PathVariable Long id,  @Valid  @RequestBody Comment comment) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.getComments().add(comment);
        return taskRepository.save(task);
    }

    // Search tasks (Optional)
//    @GetMapping("/search")
//    public List<Task> searchTasks(@RequestParam String query) {
//        return taskRepository.findByDescriptionContaining(query); // For example, searching by description
//    }
}
