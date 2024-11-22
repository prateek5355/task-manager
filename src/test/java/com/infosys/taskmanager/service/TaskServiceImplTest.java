package com.infosys.taskmanager.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.infosys.taskmanager.dto.CommentDto;
import com.infosys.taskmanager.dto.TaskDto;
import com.infosys.taskmanager.entity.Comment;
import com.infosys.taskmanager.entity.Task;
import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;
import com.infosys.taskmanager.repository.TaskRepository;

@SpringBootTest
public class TaskServiceImplTest {
	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskServiceImpl taskService;

	String test;
	Task task;
	TaskDto taskDto;
	Pageable pageable;
	List<Comment> comments;
	Comment comment;
	Page<Task> page;
	List<Task> tasks;
	CommentDto commentDto;

	@BeforeEach
	void setUp() {
		test = "Test";
		pageable = (Pageable) PageRequest.of(0, 20);
		tasks = new ArrayList<Task>();

		task = new Task();
		comment = new Comment();
		comments = new ArrayList<Comment>();

		comment.setAuthor(test);
		comment.setCreatedAt(new Date());
		comment.setId((long) 123);
		comment.setText(test);
		comments.add(comment);

		commentDto = new CommentDto();
		commentDto.setAuthor(test);
		commentDto.setText(test);

		task.setId((long) 1);
		task.setComments(comments);
		task.setAssignee(test);
		task.setDescription(test);
		task.setDueDate(new Date());
		task.setPriority(Priority.HIGH);
		task.setTitle(test);
		task.setCreatedAt(new Date());
		task.setCreator(test);
		task.setStatus(Status.DONE);
		task.setUpdatedAt(new Date());

		taskDto = new TaskDto();
		taskDto.setAssignee(test);
		taskDto.setDescription(test);
		taskDto.setDueDate(new Date());
		taskDto.setPriority(Priority.HIGH);
		taskDto.setTitle(test);
		tasks.add(task);
		page = new PageImpl<Task>(tasks, pageable, 0);
	}

	@Test
	public void createTask() {
//		when(taskRepository.findByCreator(test, pageable)).thenReturn(page);
//		taskService.createTask(taskDto);
//		Task response = taskService.createTask(taskDto);
//		assertEquals(response.getTitle(), taskDto.getTitle());
	}

	@Test
	public void listTasks() {
		when(taskRepository.findAll()).thenReturn(tasks);
//		taskService.listTasks(test, test, 1, 10);
	}

	@Test
	public void getTask() {
		when(taskRepository.findById((long) 1)).thenReturn(Optional.of(task));
		taskService.getTask((long) 1);
	}

	@Test
	public void updateTask() {
		when(taskRepository.findById((long) 1)).thenReturn(Optional.of(task));
		when(taskRepository.save(task)).thenReturn(task);
//		taskService.updateTask((long) 1, task);
	}

	@Test
	public void deleteTask() {
		taskService.deleteTask((long) 1);
	}

	@Test
	public void addComment() {
		when(taskRepository.findById((long) 1)).thenReturn(Optional.of(task));
		when(taskRepository.save(task)).thenReturn(task);
		taskService.addComment((long) 1, commentDto);
	}

	@Test
	public void searchTasks() {
		when(taskRepository.findByTitleAndDescription(test)).thenReturn(tasks);
		when(taskRepository.save(task)).thenReturn(task);
		taskService.searchTasks(test);
	}

}
