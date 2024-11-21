package com.infosys.taskmanager.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    @NotNull(message = "Text cannot be null")
    @Size(min = 1, max = 500, message = "Comment text must be between 1 and 500 characters")
    private String text;
    @NotNull(message = "Author cannot be null")
    private String author;
}


