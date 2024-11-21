package com.infosys.taskmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Text cannot be null")
    @Size(min = 1, max = 500, message = "Comment text must be between 1 and 500 characters")
    private String text;

    private String author;
    private Date createdAt;
}

