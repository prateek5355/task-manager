package com.infosys.taskmanager.dto;

import com.infosys.taskmanager.entity.Task;
import lombok.Data;

import java.util.List;

@Data
public class TaskResponse {
    private List<Task> tasks;
    private int total;
}
