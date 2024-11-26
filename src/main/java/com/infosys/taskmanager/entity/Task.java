package com.infosys.taskmanager.entity;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status = Status.TODO;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.LOW;
    private String assignee;
    private String creator ="current-user" ;

    private Date dueDate;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;
}
