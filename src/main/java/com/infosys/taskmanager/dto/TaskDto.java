package com.infosys.taskmanager.dto;

import java.util.Date;

import com.infosys.taskmanager.enums.Priority;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDto {
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;
    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;
    @NotNull(message = "Priority cannot be null")
    private Priority priority;
    @NotNull(message = "Due date cannot be null")
    @FutureOrPresent(message = "Due date must be in the present or future")
    private Date dueDate;
    @NotNull(message = "Assignee cannot be null")
    private String assignee;
}
