package com.infosys.taskmanager.entity;

import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING) // Ensures the enum is stored as a string in the database
    private Status status; // TODO | IN_PROGRESS | DONE

    @NotNull(message = "Priority cannot be null")
    @Enumerated(EnumType.STRING) // Ensure that the enum is stored as a string in the database
    private Priority priority; // Low | Medium | High

    private String assignee;

    private String creator;

    @NotNull(message = "Due date cannot be null")
    @FutureOrPresent(message = "Due date must be in the present or future")
    private Date dueDate;

    private Date createdAt;
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
