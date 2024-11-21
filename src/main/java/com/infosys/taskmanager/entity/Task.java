package com.infosys.taskmanager.entity;

import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING) // Ensures the enum is stored as a string in the database
    private Status status; // TODO | IN_PROGRESS | DONE

    @Enumerated(EnumType.STRING) // Ensure that the enum is stored as a string in the database
    private Priority priority; // Low | Medium | High

    private String assignee;
    private String creator;

    private Date dueDate;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;
}
