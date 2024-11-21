package com.infosys.taskmanager.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.taskmanager.dto.CommentDto;
import com.infosys.taskmanager.dto.TaskDeleteResponse;
import com.infosys.taskmanager.dto.TaskDto;
import com.infosys.taskmanager.entity.Comment;
import com.infosys.taskmanager.entity.Task;
import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;
import com.infosys.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {TaskController.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private Task task;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(taskController).build();

    }

    @Test
    public void testCreateTask() throws Exception {
        task = new Task();
        task.setId(1L);
        task.setTitle("Fix Login Bug");
        task.setStatus(Status.TODO);
        task.setPriority(Priority.HIGH);
        task.setAssignee("john.doe");
        task.setCreator("admin");
        when(taskService.createTask(any(TaskDto.class))).thenReturn(task);
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Fix Login Bug");
        taskDto.setDescription("Fix SSO login issue");
        taskDto.setPriority(Priority.HIGH);
        taskDto.setDueDate(new Date());
        taskDto.setAssignee("john.doe");
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(taskDto);
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Fix Login Bug"));
    }

    @Test
    public void testGetTask() throws Exception {
        task = new Task();
        task.setId(1L);
        task.setTitle("Fix Login Bug");
        task.setStatus(Status.TODO);
        task.setPriority(Priority.HIGH);
        task.setAssignee("john.doe");
        task.setCreator("admin");
        when(taskService.getTask(anyLong())).thenReturn(task);
        mockMvc.perform(get("/api/tasks/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Fix Login Bug"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        task = new Task();
        task.setId(1L);
        task.setTitle("Fix Login Bug");
        task.setStatus(Status.TODO);
        task.setPriority(Priority.HIGH);
        task.setAssignee("john.doe");
        task.setCreator("admin");
        TaskDto taskDto = new TaskDto();
        taskDto.setAssignee("jane.doe");
        taskDto.setTitle("Fix Login Bug");
        when(taskService.updateTask(any(Long.class), any(TaskDto.class))).thenReturn(task);
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(taskDto);
        mockMvc.perform(put("/api/tasks/{id}", 1L)
                        .contentType("application/json")
                        .content(request)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Fix Login Bug"))
                .andExpect(jsonPath("$.assignee").value("john.doe"))
                .andExpect(jsonPath("$.priority").value("HIGH"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        TaskDeleteResponse taskDeleteResponse = new TaskDeleteResponse();
        taskDeleteResponse.setMessage("Task deleted successfully");
        when(taskService.deleteTask(anyLong())).thenReturn(taskDeleteResponse);
        mockMvc.perform(delete("/api/tasks/{id}", 1L))
                .andExpect(status().isOk())  // Expect HTTP status 200 OK
                .andExpect(jsonPath("$.message").value("Task deleted successfully"));  // Expect the response message to be correct
    }

    @Test
    public void testAddComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setText("This is a comment about the task.");
        commentDto.setAuthor("john.doe");
        Comment comment = new Comment();
        comment.setAuthor(commentDto.getAuthor());
        comment.setText(commentDto.getText());
        List<Comment> commentDtoList = new ArrayList<>();
        commentDtoList.add(comment);
        task = new Task();
        task.setId(1L);
        task.setTitle("Fix Login Bug");
        task.setStatus(Status.TODO);
        task.setPriority(Priority.HIGH);
        task.setAssignee("john.doe");
        task.setCreator("admin");
        task.setComments(commentDtoList);
        when(taskService.addComment(anyLong(), any(CommentDto.class))).thenReturn(task);
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(commentDto);
        mockMvc.perform(post("/api/tasks/{id}/comments", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comments[0].text").value("This is a comment about the task."))
                .andExpect(jsonPath("$.comments[0].author").value("john.doe"));
    }

//    @Test
//    public void testListTasks() throws Exception {
//        when(taskService.listTasks(any(), any(), anyInt(), anyInt())).thenReturn(Collections.singletonList(task));
//
//        mockMvc.perform(get("/api/tasks")
//                        .param("status", "TODO")
//                        .param("priority", "High")
//                        .param("page", "0")
//                        .param("size", "10")
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].title").value("Fix Login Bug"));
//    }

//    @Test
//    public void testSearchTasks() throws Exception {
//        when(taskService.searchTasks(any())).thenReturn(Collections.singletonList(task));
//
//        mockMvc.perform(get("/api/tasks/search")
//                        .param("query", "SSO")
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].title").value("Fix Login Bug"));
//    }
}

