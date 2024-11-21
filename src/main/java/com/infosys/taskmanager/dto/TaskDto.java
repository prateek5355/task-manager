package com.infosys.taskmanager.dto;

import java.util.Date;
import java.util.List;

import com.infosys.taskmanager.entity.Comment;
import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String assignee;
    private String creator;
    private Date dueDate;
    private Date createdAt;
    private Date updatedAt;
    private List<Comment> comments;
}
