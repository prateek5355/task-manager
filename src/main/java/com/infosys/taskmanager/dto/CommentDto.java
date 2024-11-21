package com.infosys.taskmanager.dto;

import java.util.Date;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String text;
    private String author;
    private Date createdAt;
}

